package domain;

import java.util.Arrays;

public class Sample {

	final static long NO_NUMBER = -1;

	private double[] data;

	public Sample(String data) {
		String array[] = splitString(data);
		this.data = Arrays.stream(array).mapToDouble(Double::parseDouble).toArray();
	}

	public long getStartTime() {
		return (long) data[0];
	}

	public boolean hasTriggerTime() {
		return getTriggerTime() > 0;
	}

	public int getLabel() {
		return (int) data[1];
	}

	public long getTriggerTime() {
		int triggerId = getLabel();
		return (long) data[1 + triggerId];
	}

	private String[] splitString(String data) {
		return data.split(",");
	}

	private long number = NO_NUMBER;

	public Sample(long number, String data) {
		this(data);
		this.number = number;
	}

	public long getNumber() {
		return number;
	}
}
