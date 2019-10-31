package de.fhg.iais.roberta.worker;

import de.fhg.iais.roberta.bean.UsedHardwareBean;
import de.fhg.iais.roberta.components.Project;
import de.fhg.iais.roberta.visitor.validate.AbstractProgramValidatorVisitor;
import de.fhg.iais.roberta.visitor.validate.Calliope2017ValidatorVisitor;

public class Calliope2017BoardValidatorWorker extends AbstractValidatorWorker {

    @Override
    protected AbstractProgramValidatorVisitor getVisitor(Project project, UsedHardwareBean.Builder builder) {
        return new Calliope2017ValidatorVisitor(builder, project.getConfigurationAst());
    }
}
