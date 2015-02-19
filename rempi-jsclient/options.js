// node-getopt oneline example.
// https://www.npmjs.com/package/node-getopt
var getopt = require('node-getopt').create([
        ['h' , 'host=ARG'              , 'API URL (e.g. "92.168.2.10"'],
        ['p', 'port=ARG'               , 'API port'],
        ['d', 'delay=ARG'              , 'Refresh delay in seconds'],
        ['c', 'clientId=ARG'           , 'long option.'],
        ['s', 'simulate'			   , 'Simulates GPIO functions.'],
//
//  [''  , 'color[=COLOR]'       , 'COLOR is optional'],
//  ['m' , 'multi-with-arg=ARG+' , 'multiple option with argument'],
//  [''  , 'no-comment'],
        ['h' , 'help'                , 'display this help'],
        ['v' , 'version'             , 'show version']
    ])              // create Getopt instance
    .bindHelp();     // bind option 'help' to default action

var cmd = getopt.parseSystem(); // parse command line

module.exports.opt = getopt;
module.exports.cmd = cmd;
