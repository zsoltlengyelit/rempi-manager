import RPi.GPIO as GPIO
import time
import sys
import json, requests
import commands

host = sys.argv[1]
port = sys.argv[2]
serial = commands.getoutput("cat /proc/cpuinfo | grep 'Serial' | awk '{print $3}'")
print "Serial: " + serial

GPIO.setmode(GPIO.BCM)

#GPIO.setup(23, GPIO.OUT)
for gpioNum in [2,3,4,14,15,17,18,27,22,23,24,10,9,25,11,8,7,5,6,12,13,19,16,26,20,21]:
#	try:
	GPIO.setup(gpioNum, GPIO.OUT)
	GPIO.output(gpioNum, False)	  	
#	except Exception:
#		pass


while True:

	url = 'http://' + host + ':' + port + '/api/client/'+serial+'/state'
	resp = requests.get(url=url)
	#data = json.loads(resp.text)
	data = resp.json()
	print data['gpio']

	for gpioName in data['gpio']:
		state = data['gpio'][gpioName]
		gpioNum = int(gpioName[5:])
		enabled = state['enabled']

		GPIO.setup(gpioNum, GPIO.OUT)
		GPIO.output(gpioNum, enabled)		

		print "GPIO " + str(gpioNum) + ": " + ('enable' if enabled else 'disabled')

#	GPIO.output(4, vari)
#	vari = not vari	
	time.sleep(2)

GPIO.cleanup()
