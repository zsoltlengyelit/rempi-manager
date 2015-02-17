package org.landasource.rempi.manager.core.gpio;

/**
 *
 * @author Zsolti
 *
 */
public class PinState {

	private PinMode mode;

	private boolean enabled;

	public PinState() {
		super();
	}

	public PinState(final PinMode mode, final boolean enabled) {
		super();
		this.mode = mode;
		this.enabled = enabled;
	}

	public PinMode getMode() {
		return mode;
	}

	public void setMode(final PinMode mode) {
		this.mode = mode;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "[" + mode + ":" + enabled + "]";
	}

}
