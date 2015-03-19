package org.landasource.rempi.manager.core.notifications.device;

public class DeviceCheckinDTO {

	private Long deviceId;

	private String deviceName;

	/**
	 * In milis
	 */
	private Long time;

	private String status;

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(final Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(final String deviceName) {
		this.deviceName = deviceName;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(final Long time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

}
