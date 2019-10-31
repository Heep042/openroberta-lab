package de.fhg.iais.roberta.worker;

import de.fhg.iais.roberta.bean.UsedHardwareBean;
import de.fhg.iais.roberta.bean.UsedMethodBean;
import de.fhg.iais.roberta.components.Project;
import de.fhg.iais.roberta.components.vorwerk.VorwerkConfiguration;
import de.fhg.iais.roberta.visitor.collect.VorwerkUsedHardwareCollectorVisitor;
import de.fhg.iais.roberta.visitor.validate.AbstractCollectorVisitor;

public final class VorwerkUsedHardwareCollectorWorker extends AbstractUsedHardwareCollectorWorker {
    @Override
    protected AbstractCollectorVisitor getVisitor(
        Project project, UsedHardwareBean.Builder usedHardwareBeanBuilder, UsedMethodBean.Builder usedMethodBeanBuilder) {
        // TODO workaround: Vorwerk currently has a combination of a hard coded configuration in VorwerkConfiguration and one in the blockly
        // TODO configuration block (ip, port, username, password). The front end configuration is read and created in the project. However, for the hardware
        // TODO checking the actual hardcoded configuration is needed. This should be removed once the configuration is correctly saved in the default xml.
        return new VorwerkUsedHardwareCollectorVisitor(usedHardwareBeanBuilder, new VorwerkConfiguration("", "", "", ""));
    }
}