package de.fhg.iais.roberta.worker;

import de.fhg.iais.roberta.bean.IProjectBean;
import de.fhg.iais.roberta.components.CalliopeConfiguration;
import de.fhg.iais.roberta.components.Project;
import de.fhg.iais.roberta.visitor.codegen.CalliopeCppVisitor;
import de.fhg.iais.roberta.visitor.lang.codegen.AbstractLanguageVisitor;

public class CalliopeCxxGeneratorWorker extends AbstractLanguageGeneratorWorker {

    @Override
    protected AbstractLanguageVisitor getVisitor(Project project, IProjectBean... beans) {
        return new CalliopeCppVisitor(project.getProgramAst().getTree(), new CalliopeConfiguration.Builder().build(), beans);
    }
}
