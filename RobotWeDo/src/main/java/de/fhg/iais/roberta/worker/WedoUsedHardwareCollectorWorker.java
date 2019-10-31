package de.fhg.iais.roberta.worker;

import de.fhg.iais.roberta.bean.UsedHardwareBean;
import de.fhg.iais.roberta.bean.UsedMethodBean;
import de.fhg.iais.roberta.components.Project;
import de.fhg.iais.roberta.visitor.collect.WedoUsedHardwareCollectorVisitor;
import de.fhg.iais.roberta.visitor.validate.AbstractCollectorVisitor;

public final class WedoUsedHardwareCollectorWorker extends AbstractUsedHardwareCollectorWorker {
    @Override
    protected AbstractCollectorVisitor getVisitor(
        Project project, UsedHardwareBean.Builder usedHardwareBeanBuilder, UsedMethodBean.Builder usedMethodBeanBuilder) {
        return new WedoUsedHardwareCollectorVisitor(usedHardwareBeanBuilder, project.getConfigurationAst());
    }
}