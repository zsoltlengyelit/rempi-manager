package org.landasource.rempi.manager.core.gpio;

/**
 *
 * @author Zsolti
 *
 */
public enum GpioPin {

	/** */
	GPIO_2(2),
	/** */
	GPIO_3(3),
	/** */
	GPIO_7(7),
	/** */
	GPIO_8(8),
	/** */
	GPIO_9(9),
	/** */
	GPIO_10(10),
	/** */
	GPIO_11(11),
	/** */
	GPIO_14(14),
	/** */
	GPIO_15(15),
	/** */
	GPIO_17(17),
	/** */
	GPIO_18(18),
	/** */
	GPIO_22(22),
	/** */
	GPIO_23(23),
	/** */
	GPIO_24(24),
	/** */
	GPIO_27(27);

	private int pinNumber;

	private GpioPin(final int pinNumber) {
		this.pinNumber = pinNumber;
	}

	public int getPinNumber() {
		return pinNumber;
	}

	public static GpioPin byIndex(final int intValue) {
		for (final GpioPin pin : values()) {
			if (pin.pinNumber == intValue) {
				return pin;
			}
		}
		return null;

	}

}
