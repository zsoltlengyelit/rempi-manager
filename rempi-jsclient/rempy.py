import RPi.GPIO as GPIO
import time
import sys
import json, requests
import commands

delay = 8
requestTimeout = 10
max_retry_count=10
retry_sleep=5
host = sys.argv[1]
port = sys.argv[2]
serial = commands.getoutput("cat /proc/cpuinfo | grep 'Serial' | awk '{print $3}'")
print "Serial: " + serial

GPIO.setmode(GPIO.BCM)
validPins = [2,3,4,14,15,17,18,27,22,23,24,10,9,25,11,8,7,5,6,12,13,19,16,26,20,21]

#time.sleep(5) # wait for 3g

def close_pins():
	for gpioNum in validPins:
		GPIO.setup(gpioNum, GPIO.OUT)
		GPIO.output(gpioNum, False)	  	
	return

def get_bin_state():
	data = None
	tries=0

	url = 'http://' + host + ':' + port + '/api/client/'+serial+'/hexstate'

	while (data is None) and (tries < max_retry_count):
		tries += 1
		try:
			resp = requests.get(url=url, timeout = requestTimeout, allow_redirects=False)
			data = resp.text		
			if len(data) == 0:
				print "Empty response"
				data = None
				time.sleep(retry_sleep)
			else:
				data = bin(int(data, 16))[2:].zfill(40)	
		except requests.exceptions.Timeout:
			data = None
			time.sleep(retry_sleep)
			print 'Request time out'
		except requests.exceptions.ConnectionError:
			data = None
			time.sleep(retry_sleep)
		except:
			time.sleep(retry_sleep)
			print "Unhandled error: %s" % (sys.exc_info()[0])

	return data

# main loop
close_pins()

while True:
	try:

		state = get_bin_state()
		
		if(state is None):
			close_pins()
			exit() # fail

		for gpioNum in validPins:		
			enabled = True if state[gpioNum] == '1' else False

			GPIO.setup(gpioNum, GPIO.OUT)
			GPIO.output(gpioNum, enabled)		

			print "GPIO " + str(gpioNum) + ": " + ('enable' if enabled else 'disabled')
		
		time.sleep(delay)
	except:
		close_pins()

close_pins();
GPIO.cleanup()
