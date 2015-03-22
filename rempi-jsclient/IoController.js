/**

 */

 // opened channels. Key: channel id
//var opened = {};
var openedGpios = {};
// var pinMapping = {
//     "3" : 0,
//     "5" : 1,
//     "7" : 4,
//     "8" : 14,
//     "10": 15,
//     "11": 17,
//     "12": 18,
//     "13": 21,
//     "15": 22,
//     "16": 23,
//     "18": 24,
//     "19": 10,
//     "21": 9,
//     "22": 25,
//     "23": 11,
//     "24": 8,
//     "26": 7,

//     // Model A+ and Model B+ pins
//     "29": 5,
//     "31": 6,
//     "32": 12,
//     "33": 13,
//     "35": 19,
//     "36": 16,
//     "37": 26,
//     "38": 20,
//     "40": 21
// };
// pinMapping["3"] = 2;
// pinMapping["5"] = 3;
// pinMapping["13"] = 27;


// var reverseMap = {};
// for (var k in pinMapping) {
//     reverseMap[pinMapping[k]] = k;
// }


/**
 * @param simulate use GPIO?
 */
function Controller(simulate) {

    var gpio = simulate ? require('./pi-gpio-sim') : require('onoff').Gpio;
    
    function writeOut(ch, val, cb) {
        cb = cb || function () {
        };
        //gpio.write(ch, val, cb);
        openedGpios[ch].writeSync();
        cb();
    }


    function writeOpenOut(gpioNum, val, cb) {

        if (openedGpios[gpioNum]) {
            writeOut(gpioNum, val, cb);
        } else {            
            openedGpios[gpioNum] = new Gpio(Number(gpioNum), 'out');
            writeOut(gpioNum, val, cb);
        }
    }

    this.forceCloseOpens = function(){
      for(var ch in openedGpios){
          this.forceClose(ch);
      }
    };

    this.forceCloseAll = function () {
        for (var ch = 0; ch < 40; ch++) {
            console.log('Close:' + ch);
            try {
                gpio.write(ch, 0, function () {
                    gpio.close(ch);
                });
            } catch (e) {
                console.error(e);
            }
        }
    };

    this.forceClose = function (ch) {
        if (openedGpios[ch]) {
            openedGpios[ch].writeSync(0);
            openedGpios[ch].unexport();
            delete openedGpios[ch];
        }
    };


    this.changeState = function (state) {
        console.log('Change state ------------------------');

        for (var gpioKey in state.gpio) {
            var gpioNum = gpioKey.substr(5);
            
            if(!openedGpios[gpioNum]){
               
            }
            var s = state.gpio[gpioKey];

            console.log('W', gpioNum, s.enabled);

            writeOpenOut(gpioNum, s.enabled);
        }
    };

    // init
    this.forceCloseAll();
}

function exit() {
  for(var i in openedGpios){
    openedGpios[ch].writeSync(0);
    openedGpios[ch].unexport();
  }
  process.exit();
}


process.on('SIGINT', exit);

module.exports = Controller;