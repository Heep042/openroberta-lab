package de.fhg.iais.roberta.codegen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.fhg.iais.roberta.blockly.generated.BlockSet;
import de.fhg.iais.roberta.components.Configuration;
import de.fhg.iais.roberta.components.ev3.EV3Configuration;
import de.fhg.iais.roberta.components.ev3.JavaSourceCompiler;
import de.fhg.iais.roberta.factory.IRobotFactory;
import de.fhg.iais.roberta.inter.mode.action.ILanguage;
import de.fhg.iais.roberta.transformer.BlocklyProgramAndConfigTransformer;
import de.fhg.iais.roberta.transformer.ev3.Jaxb2Ev3ConfigurationTransformer;
import de.fhg.iais.roberta.util.Key;
import de.fhg.iais.roberta.util.PluginProperties;
import de.fhg.iais.roberta.util.jaxb.JaxbHelper;
import de.fhg.iais.roberta.visitor.codegen.Ev3JavaVisitor;

public class Ev3LejosCompilerWorkflow extends AbstractCompilerWorkflow {
    private static final Logger LOG = LoggerFactory.getLogger(Ev3LejosCompilerWorkflow.class);

    public Ev3LejosCompilerWorkflow(PluginProperties pluginProperties) {
        super(pluginProperties);
    }

    @Override
    public String generateSourceCode(String token, String programName, BlocklyProgramAndConfigTransformer data, ILanguage language) {
        return Ev3JavaVisitor.generate(programName, (EV3Configuration) data.getBrickConfiguration(), data.getProgramTransformer().getTree(), true, language);
    }

    @Override
    public Key compileSourceCode(String token, String programName, String sourceCode, ILanguage language, Object flagProvider) {
        //Ev3CompilerWorkflow.LOG.info("generated code:\n{}", sourceCode); // only needed for EXTREME debugging
        try {
            storeGeneratedProgram(token, programName, sourceCode, ".java");
        } catch ( Exception e ) {
            Ev3LejosCompilerWorkflow.LOG.error("Storing the generated program into directory " + token + " failed", e);
            return Key.COMPILERWORKFLOW_ERROR_PROGRAM_STORE_FAILED;
        }

        final String compilerResourcesDir = pluginProperties.getCompilerResourceDir();
        final String tempDir = pluginProperties.getTempDir();

        JavaSourceCompiler scp = new JavaSourceCompiler(programName, sourceCode, compilerResourcesDir);
        boolean isSuccess = scp.compileAndPackage(tempDir, token);
        if ( !isSuccess ) {
            LOG.error("build exception. Messages from the build script are:\n" + scp.getCompilationMessages());
            return Key.COMPILERWORKFLOW_ERROR_PROGRAM_COMPILE_FAILED;
        } else {
            LOG.info("jar for program {} generated successfully", programName);
            return Key.COMPILERWORKFLOW_SUCCESS;
        }
    }

    @Override
    public Configuration generateConfiguration(IRobotFactory factory, String blocklyXml) throws Exception {
        BlockSet project = JaxbHelper.xml2BlockSet(blocklyXml);
        Jaxb2Ev3ConfigurationTransformer transformer = new Jaxb2Ev3ConfigurationTransformer(factory);
        return transformer.transform(project);
    }

    @Override
    public String getCompiledCode() {
        return null;
    }
}
