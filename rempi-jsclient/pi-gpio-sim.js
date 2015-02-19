"use strict";

function isNumber(number) {
	return !isNaN(parseInt(number, 10));
}

function noop() {}

var gpio = {
	rev: 2,

	open: function(pinNumber, options, callback) {
		(callback || noop)();
	},

	setDirection: function(pinNumber, direction, callback) {
		(callback || noop)();
	},

	getDirection: function(pinNumber, callback) {				
		(callback || noop)(null, "out");
		
	},

	close: function(pinNumber, callback) {		
		(callback || noop)();
	},

	read: function(pinNumber, callback) {
		(callback || noop)(null, parseInt("0", 10));		
	},

	write: function(pinNumber, value, callback) {
		(callback || noop)();
	}
};

gpio.export = gpio.open;
gpio.unexport = gpio.close;

module.exports = gpio;