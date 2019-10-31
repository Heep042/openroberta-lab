package de.fhg.iais.roberta.worker.codegen;

import de.fhg.iais.roberta.bean.IProjectBean;
import de.fhg.iais.roberta.components.Project;
import de.fhg.iais.roberta.visitor.codegen.BotnrollCppVisitor;
import de.fhg.iais.roberta.visitor.lang.codegen.AbstractLanguageVisitor;
import de.fhg.iais.roberta.worker.AbstractLanguageGeneratorWorker;

public class BotnrollCxxGeneratorWorker extends AbstractLanguageGeneratorWorker {

    @Override
    protected AbstractLanguageVisitor getVisitor(Project project, IProjectBean... beans) {
        return new BotnrollCppVisitor(project.getProgramAst().getTree(), project.getConfigurationAst(), beans);
    }
}
