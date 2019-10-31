package de.fhg.iais.roberta.worker;

import de.fhg.iais.roberta.bean.IProjectBean;
import de.fhg.iais.roberta.components.Project;
import de.fhg.iais.roberta.visitor.codegen.NxtNxcVisitor;
import de.fhg.iais.roberta.visitor.lang.codegen.AbstractLanguageVisitor;

public final class NxtCxxGeneratorWorker extends AbstractLanguageGeneratorWorker {

    @Override
    protected AbstractLanguageVisitor getVisitor(Project project, IProjectBean... beans) {
        return new NxtNxcVisitor(project.getProgramAst().getTree(), project.getConfigurationAst(), beans);
    }
}
