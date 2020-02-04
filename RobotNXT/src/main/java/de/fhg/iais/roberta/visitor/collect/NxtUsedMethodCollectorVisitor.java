package de.fhg.iais.roberta.visitor.collect;

import de.fhg.iais.roberta.bean.UsedMethodBean;
import de.fhg.iais.roberta.mode.general.IndexLocation;
import de.fhg.iais.roberta.syntax.action.display.ShowTextAction;
import de.fhg.iais.roberta.syntax.lang.functions.Function;
import de.fhg.iais.roberta.syntax.lang.functions.FunctionNames;
import de.fhg.iais.roberta.syntax.lang.functions.IndexOfFunct;
import de.fhg.iais.roberta.syntax.lang.functions.ListGetIndex;
import de.fhg.iais.roberta.syntax.lang.functions.ListSetIndex;
import de.fhg.iais.roberta.syntax.lang.functions.MathOnListFunct;
import de.fhg.iais.roberta.syntax.lang.functions.MathSingleFunct;
import de.fhg.iais.roberta.typecheck.BlocklyType;
import de.fhg.iais.roberta.util.dbc.DbcException;
import de.fhg.iais.roberta.visitor.codegen.NxtNxcVisitor;

public class NxtUsedMethodCollectorVisitor extends AbstractUsedMethodCollectorVisitor implements INxtCollectorVisitor {
    public NxtUsedMethodCollectorVisitor(UsedMethodBean.Builder builder) {
        super(builder);
    }

    @Override
    public Void visitMathOnListFunct(MathOnListFunct<Void> mathOnListFunct) {
        switch ( mathOnListFunct.getFunctName() ) {
            case AVERAGE:
                this.builder.addUsedMethod(NxtMethods.IDTY);
                break;
            case MEDIAN:
                this.builder.addUsedMethod(NxtMethods.IDTY);
                this.builder.addUsedMethod(NxtMethods.INSERTION_SORT);
                break;
            case MODE:
                this.builder.addUsedMethod(NxtMethods.INSERTION_SORT);
                break;
            case STD_DEV:
                this.builder.addUsedMethod(NxtMethods.IDTY);
                this.builder.addUsedMethod(FunctionNames.POWER);
                this.builder.addUsedMethod(FunctionNames.AVERAGE);
                break;
            default:
                break; // no action necessary
        }
        return super.visitMathOnListFunct(mathOnListFunct);
    }

    @Override
    public Void visitMathSingleFunct(MathSingleFunct<Void> mathSingleFunct) {
        switch ( mathSingleFunct.getFunctName() ) {
            case ACOS:
                this.builder.addUsedMethod(FunctionNames.ASIN);
                this.builder.addUsedMethod(FunctionNames.POWER);
                this.builder.addUsedMethod(NxtMethods.FACTORIAL);
                break;
            case ASIN:
            case COS:
            case SIN:
                this.builder.addUsedMethod(FunctionNames.POWER);
                this.builder.addUsedMethod(NxtMethods.FACTORIAL);
                break;
            case ATAN:
            case EXP:
            case LN:
                this.builder.addUsedMethod(FunctionNames.POWER);
                break;
            case LOG10:
                this.builder.addUsedMethod(FunctionNames.LN);
                this.builder.addUsedMethod(FunctionNames.POWER);
                break;
            case ROUND:
            case ROUNDUP:
                this.builder.addUsedMethod(FunctionNames.ROUNDDOWN);
                break;
            case TAN:
                this.builder.addUsedMethod(FunctionNames.SIN);
                this.builder.addUsedMethod(FunctionNames.COS);
                this.builder.addUsedMethod(FunctionNames.POWER);
                this.builder.addUsedMethod(NxtMethods.FACTORIAL);
                break;
            default:
                break; // no action necessary
        }
        return super.visitMathSingleFunct(mathSingleFunct);
    }

    @Override
    public Void visitListGetIndex(ListGetIndex<Void> listGetIndex) {
        this.builder.addUsedMethod(NxtMethods.SANITISE_FROM_START);
        this.builder.addUsedMethod(NxtMethods.SANITISE_FROM_END);
        return super.visitListGetIndex(listGetIndex);
    }

    @Override
    public Void visitListSetIndex(ListSetIndex<Void> listSetIndex) {
        this.builder.addUsedMethod(NxtMethods.SANITISE_FROM_START);
        this.builder.addUsedMethod(NxtMethods.SANITISE_FROM_END);
        return super.visitListSetIndex(listSetIndex);
    }

    @Override
    public Void visitIndexOfFunct(IndexOfFunct<Void> indexOfFunct) {
        this.builder.addUsedMethod(NxtNxcVisitor.getMethodForIndexOf(indexOfFunct));
        this.builder.addUsedMethod(NxtMethods.IDTY);
        return super.visitIndexOfFunct(indexOfFunct);
    }

    @Override
    public Void visitShowTextAction(ShowTextAction<Void> showTextAction) {
        String method = NxtNxcVisitor.getMethodForShowText(showTextAction);
        if (method.equals("BoolOut")) {
            this.builder.addUsedMethod(NxtMethods.BOOL_OUT);
        }
        if (method.equals("ColorOut")) {
            this.builder.addUsedMethod(NxtMethods.COLOR_OUT);
        }
        return super.visitShowTextAction(showTextAction);
    }
}
