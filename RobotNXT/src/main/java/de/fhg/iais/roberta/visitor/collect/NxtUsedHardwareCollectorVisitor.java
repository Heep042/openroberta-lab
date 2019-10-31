package de.fhg.iais.roberta.visitor.collect;

import de.fhg.iais.roberta.bean.UsedHardwareBean;
import de.fhg.iais.roberta.bean.UsedMethodBean;
import de.fhg.iais.roberta.components.ConfigurationAst;
import de.fhg.iais.roberta.components.UsedActor;
import de.fhg.iais.roberta.components.UsedSensor;
import de.fhg.iais.roberta.syntax.SC;
import de.fhg.iais.roberta.syntax.action.light.LightAction;
import de.fhg.iais.roberta.syntax.action.sound.PlayNoteAction;
import de.fhg.iais.roberta.syntax.action.sound.ToneAction;
import de.fhg.iais.roberta.syntax.action.sound.VolumeAction;
import de.fhg.iais.roberta.visitor.IVisitor;

/**
 * This class is implementing {@link IVisitor}. All methods are implemented and they append a human-readable JAVA code representation of a phrase to a
 * StringBuilder. <b>This representation is correct JAVA code.</b> <br>
 */
public final class NxtUsedHardwareCollectorVisitor extends AbstractUsedHardwareCollectorVisitor {

    private final UsedMethodBean.Builder usedMethodBeanBuilder;

    public NxtUsedHardwareCollectorVisitor(
        UsedHardwareBean.Builder usedHardwareBeanBuilder,
        UsedMethodBean.Builder usedMethodBeanBuilder,
        ConfigurationAst robotConfiguration) {
        super(usedHardwareBeanBuilder, robotConfiguration);
        this.usedMethodBeanBuilder = usedMethodBeanBuilder;
    }

    @Override
    public Void visitLightAction(LightAction<Void> lightAction) {
        this.builder.addUsedSensor(new UsedSensor(lightAction.getPort(), SC.HT_COLOR, "COLOR"));
        return null;
    }

    @Override
    public Void visitVolumeAction(VolumeAction<Void> volumeAction) {
        super.visitVolumeAction(volumeAction);
        this.builder.addUsedActor(new UsedActor("", SC.SOUND));
        return null;
    }

    @Override
    public Void visitToneAction(ToneAction<Void> toneAction) {
        super.visitToneAction(toneAction);
        this.builder.addUsedActor(new UsedActor("", SC.SOUND));
        return null;
    }

    @Override
    public Void visitPlayNoteAction(PlayNoteAction<Void> playNoteAction) {
        super.visitPlayNoteAction(playNoteAction);
        this.builder.addUsedActor(new UsedActor("", SC.SOUND));
        return null;
    }
}