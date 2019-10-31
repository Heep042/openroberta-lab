package de.fhg.iais.roberta.worker.validate;

import de.fhg.iais.roberta.bean.UsedHardwareBean;
import de.fhg.iais.roberta.components.Project;
import de.fhg.iais.roberta.visitor.validate.AbstractProgramValidatorVisitor;
import de.fhg.iais.roberta.visitor.validate.Ev3BrickValidatorVisitor;
import de.fhg.iais.roberta.worker.AbstractValidatorWorker;

public class Ev3BrickValidatorWorker extends AbstractValidatorWorker {

    @Override
    protected AbstractProgramValidatorVisitor getVisitor(Project project, UsedHardwareBean.Builder builder) {
        return new Ev3BrickValidatorVisitor(builder, project.getConfigurationAst());
    }
}
