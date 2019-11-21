define([ 'require', 'exports', 'message', 'log', 'util', 'comm', 'guiState.controller', 'program.model', 'program.controller', 'progRun.controller','import.controller', 'blocks', 'codeflask', 'jquery', 'blocks-msg'  ],
        function(require, exports, MSG, LOG, UTIL, COMM, GUISTATE_C, PROGRAM, PROG_C, PROGRUN_C, IMPORT_C, Blockly, CodeFlask, $) {

    var flask;
    var currentLanguage;
    
    function init() {
        flask = new CodeFlask('#flaskEditor', {
            language: 'java',
            lineNumbers: true,
        });
        initEvents();
    }
    exports.init = init;
    
    function setCodeLanguage(language) {
        var langToSet;
        switch (language) {
            case 'py': 
                langToSet = 'python';
                break;
            case 'java': 
                langToSet = 'java';
                break;
            case 'ino':
            case 'nxc':
            case 'cpp': 
                langToSet = 'clike';
                break;
            case 'json': 
                langToSet = 'js';
                break;
            default:
                langToSet = 'js';
        }
        flask.updateLanguage(langToSet);
        currentLanguage = langToSet;
    }
    exports.setCodeLanguage = setCodeLanguage;

    function initEvents() {
        $('#backSourceCodeEditor').onWrap('click', function() {
            $('#show-message-confirm').one('shown.bs.modal', function(e) {
                $('#confirm').off();
                $('#confirm').on('click', function(e) {
                    e.preventDefault();
                    $('#tabProgram').trigger('click');
                });
                $('#confirmCancel').off();
                $('#confirmCancel').on('click', function(e) {
                    e.preventDefault();
                    $('.modal').modal('hide');
                });
            });
            MSG.displayMessage('All your changes will be lost!', 'POPUP', '', true, false);            
            return false;
        }, "back to previous view");
        
        $('#runSourceCodeEditor').onWrap('click', function() {
            PROGRUN_C.runOnBrick(flask.getCode());
            return false;
        }, "run button clicked");
        
        $('#buildSourceCodeEditor').onWrap('click', function() {
            PROGRAM.compileN(GUISTATE_C.getProgramName(), flask.getCode(), GUISTATE_C.getLanguage(), function(result) {
                if (result.rc == "ok") {
                    MSG.displayMessage(result.message, 'POPUP', '', false, false);
                } else {
                    MSG.displayInformation(result, result.message, result.message, GUISTATE_C.getProgramName());
                }
            });
            return false;
        }, "build button clicked");
        
        $('#downloadSourceCodeEditor').onWrap('click', function() {
            var filename = GUISTATE_C.getProgramName() + '.' + GUISTATE_C.getSourceCodeFileExtension();
            UTIL.download(filename, flask.getCode());
            MSG.displayMessage("MENU_MESSAGE_DOWNLOAD", "TOAST", filename);
            return false;
        }, "download source code button clicked");
        
        $('#uploadSourceCodeEditor').onWrap('click', function() {
            IMPORT_C.importSourceCode(function(name, source) {
                flask.updateCode(source);
            });
            return false;
        }, "upload source code button clicked");
        
        $('#importSourceCodeEditor').onWrap('click', function() {
            getSourceCode();
            return false;
        }, "import from blockly button clicked");
        
        $('#tabSourceCodeEditor').onWrap('show.bs.tab', function() {
            if(currentLanguage === 'python' || currentLanguage === 'json') {
                $('#buildSourceCodeEditor').addClass('disabled');
            }
            $('#main-section').css('background-color', '#EEE');
            $('#head-navigation-robot').addClass('disabled');
            getSourceCode();
        }, "background color changed by source code editor");
        
        $('#tabSourceCodeEditor').onWrap('shown.bs.tab', function() {
            GUISTATE_C.setView('tabSourceCodeEditor');
        }, "background color changed by source code editor");

        $('#tabSourceCodeEditor').onWrap('hide.bs.tab', function() {
            $('#head-navigation-robot').removeClass('disabled');
            $('#buildSourceCodeEditor').removeClass('disabled');
            $('#main-section').css('background-color', '#FFF');
        }, "background color changed by source code editor");
    }
    
    function getSourceCode() {
        var blocklyWorkspace = GUISTATE_C.getBlocklyWorkspace();
        var dom = Blockly.Xml.workspaceToDom(blocklyWorkspace);
        var xmlProgram = Blockly.Xml.domToText(dom);
        var isNamedConfig = !GUISTATE_C.isConfigurationStandard() && !GUISTATE_C.isConfigurationAnonymous();
        var configName = isNamedConfig ? GUISTATE_C.getConfigurationName() : undefined;
        var xmlConfigText = GUISTATE_C.isConfigurationAnonymous() ? GUISTATE_C.getConfigurationXML() : undefined;
        var language = GUISTATE_C.getLanguage();
        PROGRAM.showSourceProgram(GUISTATE_C.getProgramName(), configName, xmlProgram, xmlConfigText, PROG_C.SSID, PROG_C.password, language, 
                function(result) {
            if (result.rc == "ok") {
                flask.updateCode(result.sourceCode);
            } else {
                MSG.displayInformation(result, result.message, result.message, result.parameters);
            }
        });
    }
});