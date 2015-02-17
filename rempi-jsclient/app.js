/**
 * rempi JS client
 */

var _ = require('underscore');
var cmd = require('./options');
var IoController = require('./IoController');
var rp = require('request-promise');


var l = console.log;

console.log(cmd);

var host = cmd.options['apiUrl'];

options = {
    method: 'GET',
    uri: host,
    resolveWithFullResponse: true
};
 
rp(host)
    .then(function (body) {
        console.log(body.substr(0, 10));
    })
    .catch(console.error);
