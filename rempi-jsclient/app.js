/**
 * rempi JS client
 */

var _ = require('underscore');
var options = require('./options');
var cmd = options.cmd;
var getopt = options.opt;

var host = cmd.options['host'];
var port = cmd.options['port'] || 8080;
var clientId = cmd.options['clientId'];
var reqInterval = cmd.options['delay'] || 2;
var simulate = !!cmd.options['simulate'] || false;

if (!host || !clientId) {
    getopt.showHelp();
    process.exit(1);
}

var IC = require('./IoController');
var IoController = new IC(simulate);
var http = require('http');

function call(){

    var options = {
        hostname: host,
        port: port,
        path: '/api/client/'+clientId+'/state',
        method: 'GET',
        headers: {
        }
    };

    var req = http.request(options, function(res) {
        res.setEncoding('utf8');
        res.on('data', function (chunk) {
            var state = JSON.parse(chunk);

            if(state.error){
                console.log('Error:' + state.error)
            }else{
                IoController.changeState(state);
            }
        });
    });

    req.on('error', function(e) {
        console.log('problem with request: ' + e.message);

        IoController.forceCloseOpens();

    });
    req.end();
}
// main


setInterval(call, reqInterval * 1000);
