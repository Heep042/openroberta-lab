package de.fhg.iais.roberta.worker.codegen;

import de.fhg.iais.roberta.bean.IProjectBean;
import de.fhg.iais.roberta.components.Project;
import de.fhg.iais.roberta.visitor.codegen.SenseboxCppVisitor;
import de.fhg.iais.roberta.visitor.lang.codegen.AbstractLanguageVisitor;
import de.fhg.iais.roberta.worker.AbstractLanguageGeneratorWorker;

public class SenseboxCxxGeneratorWorker extends AbstractLanguageGeneratorWorker {

    @Override
    protected AbstractLanguageVisitor getVisitor(Project project, IProjectBean... beans) {
        return new SenseboxCppVisitor(
            project.getProgramAst().getTree(),
            project.getConfigurationAst(),
            project.getSSID(),
            project.getPassword(),
            beans);
    }
}
