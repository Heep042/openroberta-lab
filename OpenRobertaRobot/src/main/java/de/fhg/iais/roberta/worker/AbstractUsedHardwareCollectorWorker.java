package de.fhg.iais.roberta.worker;

import java.util.ArrayList;
import java.util.List;

import de.fhg.iais.roberta.bean.UsedHardwareBean;
import de.fhg.iais.roberta.bean.UsedMethodBean;
import de.fhg.iais.roberta.components.Project;
import de.fhg.iais.roberta.syntax.Phrase;
import de.fhg.iais.roberta.visitor.IVisitor;
import de.fhg.iais.roberta.visitor.collect.AbstractUsedHardwareCollectorVisitor;
import de.fhg.iais.roberta.visitor.validate.AbstractCollectorVisitor;

/**
 * Uses the {@link AbstractUsedHardwareCollectorVisitor} to visit the current AST and collect all used hardware.
 * Data collected is stored in the {@link UsedHardwareBean}.
 * Currently does more than just collect the used hardware, e.g. also handles global variables.
 */
public abstract class AbstractUsedHardwareCollectorWorker implements IWorker {

    @Override
    public final void execute(Project project) {
        UsedHardwareBean.Builder usedHardwareBeanBuilder = new UsedHardwareBean.Builder();
        UsedMethodBean.Builder usedMethodBeanBuilder = new UsedMethodBean.Builder();
        AbstractCollectorVisitor visitor = this.getVisitor(project, usedHardwareBeanBuilder, usedMethodBeanBuilder);
        List<ArrayList<Phrase<Void>>> tree = project.getProgramAst().getTree();
        collectGlobalVariables(tree, visitor);
        for ( List<Phrase<Void>> phrases : tree ) {
            for ( Phrase<Void> phrase : phrases ) {
                if ( phrase.getKind().getName().equals("MAIN_TASK") ) {
                    usedHardwareBeanBuilder.setProgramEmpty(phrases.size() == 2);
                } else {
                    phrase.accept(visitor);
                }
            }
        }
        project.addWorkerResult(usedHardwareBeanBuilder.build());
        project.addWorkerResult(usedMethodBeanBuilder.build());
    }

    /**
     * Returns the appropriate visitor for this worker. Used by subclasses to keep the execute method generic.
     * Could be removed in the future, when visitors are specified in the properties as well, or inferred from the worker name.
     *
     * @param project the project
     * @param usedHardwareBeanBuilder the used hardware bean builder
     * @param usedMethodBeanBuilder the used method bean builder, only used sometimes
     * @return the appropriate visitor for the current robot
     */
    protected abstract AbstractCollectorVisitor getVisitor(
        Project project, UsedHardwareBean.Builder usedHardwareBeanBuilder, UsedMethodBean.Builder usedMethodBeanBuilder);

    private static void collectGlobalVariables(Iterable<ArrayList<Phrase<Void>>> phrasesSet, IVisitor<Void> visitor) {
        for ( List<Phrase<Void>> phrases : phrasesSet ) {
            Phrase<Void> phrase = phrases.get(1);
            if ( phrase.getKind().getName().equals("MAIN_TASK") ) {
                phrase.accept(visitor);
            }
        }
    }
}
