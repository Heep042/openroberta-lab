package de.fhg.iais.roberta.worker;

import de.fhg.iais.roberta.bean.CodeGeneratorSetupBean;
import de.fhg.iais.roberta.bean.IProjectBean;
import de.fhg.iais.roberta.bean.UsedHardwareBean;
import de.fhg.iais.roberta.bean.UsedMethodBean;
import de.fhg.iais.roberta.components.Project;
import de.fhg.iais.roberta.util.Key;
import de.fhg.iais.roberta.visitor.lang.codegen.AbstractLanguageVisitor;

/**
 * Uses the {@link AbstractLanguageVisitor} to visit the current AST and generate the robot's specific source code.
 */
public abstract class AbstractLanguageGeneratorWorker implements IWorker {

    @Override
    public final void execute(Project project) {
        UsedHardwareBean usedHardwareBean = project.optWorkerResult(UsedHardwareBean.class); // TODO currently opt for tests
        UsedMethodBean usedMethodBean = project.optWorkerResult(UsedMethodBean.class); // TODO currently opt for tests

        AbstractLanguageVisitor visitor;
        if ( (usedMethodBean != null) && (project.getRobotFactory().getPluginProperties().getStringProperty("robot.helperMethods") != null) ) {
            // Prepare bean for the code generation visitor
            CodeGeneratorSetupBean.Builder codeGenSetupBeanBuilder = new CodeGeneratorSetupBean.Builder();
            codeGenSetupBeanBuilder.setFileExtension(project.getSourceCodeFileExtension());
            codeGenSetupBeanBuilder.setHelperMethodFile(project.getRobotFactory().getPluginProperties().getStringProperty("robot.helperMethods"));
            codeGenSetupBeanBuilder.addAdditionalEnums(usedMethodBean.getAdditionalEnums());
            codeGenSetupBeanBuilder.addUsedMethods(usedMethodBean.getUsedMethods());
            visitor = this.getVisitor(project, usedHardwareBean, codeGenSetupBeanBuilder.build());
        } else {
            visitor = this.getVisitor(project, usedHardwareBean);
        }
        visitor.setStringBuilders(project.getSourceCode(), project.getIndentation());
        visitor.generateCode(project.isWithWrapping());
        project.setResult(Key.COMPILERWORKFLOW_PROGRAM_GENERATION_SUCCESS);
    }

    /**
     * Returns the appropriate visitor for this worker. Used by subclasses to keep the execute method generic.
     * Could be removed in the future, when visitors are specified in the properties as well, or inferred from the worker name.
     *
     * @param project the project
     * @return the appropriate visitor for the current robot
     */
    protected abstract AbstractLanguageVisitor getVisitor(Project project, IProjectBean... beans);
}
