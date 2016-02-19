require.config({
    baseUrl : 'js/libs',
    paths : {
        'pace' : 'pace',
        'jquery' : 'jquery/jquery-2.1.4.min',
        'jquery-ui' : 'jquery/jquery-ui-1.10.4.custom.min',
        'datatables' : 'jquery/jquery.dataTables.min',
        'jquery-validate' : 'jquery/jquery.validate.min',
        'bootstrap' : 'bootstrap/bootstrap-3.3.1-dist/dist/js/bootstrap.min',
        'blocks' : '../../blockly/blocks_compressed',
        'blocks-msg' : '../../blockly/msg/js/en',
        'blockly' : '../../blockly/blockly_compressed',

        'roberta.roberta' : '../app/roberta/roberta',
        'roberta.user-state' : '../app/roberta/roberta.user.state',
        'roberta.brickly' : '../app/roberta/brickly',
        'roberta.user' : '../app/roberta/roberta.user',
        'roberta.toolbox' : '../app/roberta/roberta.toolbox',
        'roberta.robot' : '../app/roberta/roberta.robot',
        'roberta.program' : '../app/roberta/roberta.program',
        'roberta.program.sharing' : '../app/roberta/roberta.program.sharing',
        'roberta.navigation' : '../app/roberta/roberta.navigation',
        'roberta.language' : '../app/roberta/roberta.language',
        'roberta.brick-configuration' : '../app/roberta/roberta.brick-configuration',

        'simulation.constants' : '../app/simulation/simulationLogic/constants',
        'simulation.simulation' : '../app/simulation/simulationLogic/simulation',
        'simulation.robot' : '../app/simulation/simulationLogic/robot',
        'simulation.robot.simple' : '../app/simulation/simulationLogic/robot.simple',
        'simulation.robot.draw' : '../app/simulation/simulationLogic/robot.draw',
        'simulation.robot.math' : '../app/simulation/simulationLogic/robot.math',
        'simulation.robot.rescue' : '../app/simulation/simulationLogic/robot.rescue',
        'simulation.robot.roberta' : '../app/simulation/simulationLogic/robot.roberta',
        'simulation.scene' : '../app/simulation/simulationLogic/scene',
        'simulation.math' : '../app/simulation/simulationLogic/math',
        'simulation.program.eval' : '../app/simulation/robertaLogic/program.eval',

        'rest.configuration' : '../app/roberta/rest/configuration',
        'rest.program' : '../app/roberta/rest/program',
        'rest.robot' : '../app/roberta/rest/robot',
        'rest.user' : '../app/roberta/rest/user',

        'log' : '../helper/log',
        'wrap' : '../helper/wrap',
        'comm' : '../helper/comm',
        'util' : '../helper/util',
        'message' : '../helper/msg',

        'robertaLogic.timer' : '../app/simulation/robertaLogic/timer',
        'robertaLogic.actors' : '../app/simulation/robertaLogic/actors',
        'robertaLogic.sensors' : '../app/simulation/robertaLogic/sensors',
        'robertaLogic.led' : '../app/simulation/robertaLogic/led',
        'robertaLogic.motor' : '../app/simulation/robertaLogic/motor',
        'robertaLogic.memory' : '../app/simulation/robertaLogic/memory',
        'robertaLogic.program' : '../app/simulation/robertaLogic/program'

    },
    shim : {
        'bootstrap' : {
            "deps" : [ 'jquery' ]
        },
        'blocks-msg' : {
            deps : [ 'blocks' ],
            exports : 'Blockly'
        },
        'blocks' : {
            deps : [ 'blockly' ],
            exports : 'Blockly'
        },
        'jquery-ui' : {
            deps : [ 'jquery' ]
        }
    }
});

require([ 'require', 'wrap', 'roberta.roberta', 'jquery' ], function(require) {

    $ = require('jquery');
    WRAP = require('wrap');
    ROBERTA = require('roberta.roberta');   

    $(document).ready(WRAP.fn3(ROBERTA.init, 'page init'));
});
