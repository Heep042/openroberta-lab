package de.fhg.iais.roberta.worker;

import de.fhg.iais.roberta.bean.IProjectBean;
import de.fhg.iais.roberta.components.Project;
import de.fhg.iais.roberta.visitor.codegen.RaspberryPiPythonVisitor;
import de.fhg.iais.roberta.visitor.lang.codegen.AbstractLanguageVisitor;

public final class RaspberryPiPythonGeneratorWorker extends AbstractLanguageGeneratorWorker {

    @Override
    protected AbstractLanguageVisitor getVisitor(Project project, IProjectBean... beans) {
        return new RaspberryPiPythonVisitor(
            project.getProgramAst().getTree(),
            project.getConfigurationAst(),
            beans);
    }
}
