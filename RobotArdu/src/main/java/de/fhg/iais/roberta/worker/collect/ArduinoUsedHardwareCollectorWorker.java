package de.fhg.iais.roberta.worker.collect;

import de.fhg.iais.roberta.bean.UsedHardwareBean;
import de.fhg.iais.roberta.bean.UsedMethodBean;
import de.fhg.iais.roberta.components.Project;
import de.fhg.iais.roberta.visitor.collect.ArduinoUsedHardwareCollectorVisitor;
import de.fhg.iais.roberta.visitor.validate.AbstractCollectorVisitor;
import de.fhg.iais.roberta.worker.AbstractUsedHardwareCollectorWorker;

public final class ArduinoUsedHardwareCollectorWorker extends AbstractUsedHardwareCollectorWorker {
    @Override
    protected AbstractCollectorVisitor getVisitor(
        Project project, UsedHardwareBean.Builder usedHardwareBeanBuilder, UsedMethodBean.Builder usedMethodBeanBuilder) {
        return new ArduinoUsedHardwareCollectorVisitor(usedHardwareBeanBuilder, project.getProgramAst().getTree(), project.getConfigurationAst());
    }
}
