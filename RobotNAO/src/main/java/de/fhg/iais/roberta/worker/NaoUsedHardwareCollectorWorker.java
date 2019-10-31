package de.fhg.iais.roberta.worker;

import de.fhg.iais.roberta.bean.UsedHardwareBean;
import de.fhg.iais.roberta.bean.UsedMethodBean;
import de.fhg.iais.roberta.components.Project;
import de.fhg.iais.roberta.visitor.collect.NaoUsedHardwareCollectorVisitor;
import de.fhg.iais.roberta.visitor.validate.AbstractCollectorVisitor;

public final class NaoUsedHardwareCollectorWorker extends AbstractUsedHardwareCollectorWorker {
    @Override
    protected AbstractCollectorVisitor getVisitor(
        Project project, UsedHardwareBean.Builder usedHardwareBeanBuilder, UsedMethodBean.Builder usedMethodBeanBuilder) {
        return new NaoUsedHardwareCollectorVisitor(usedHardwareBeanBuilder, project.getProgramAst().getTree(), project.getConfigurationAst());
    }
}