$(function() {

	function connect() {
		socket = new SockJS('/chat');
		stompClient = Stomp.over(socket);
		stompClient.connect('guest', 'guest', function(frame) {
			whoami = frame.headers['user-name'];
			console.log('Connected: ' + frame);
//			stompClient.subscribe('/user/queue/messages', function(message) {
//				showMessage(JSON.parse(message.body));
//			});
			stompClient.subscribe('/devices/checkin', function(message) {
				var device = JSON.parse(message.body);
				console.log(device);
				
				var status = $('#device-status-' + device.deviceId);
				
				status.show();
				status.css('background', 'green');
				status.fadeOut(2000);
				
				
			});
		});
	}

	connect();

});
