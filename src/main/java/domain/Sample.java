package domain;

import java.util.Arrays;

public class Sample {

	private double[] data;

	public Sample(String data) {
		String array[] = splitString(data);
		this.data = Arrays.stream(array).mapToDouble(Double::parseDouble).toArray();
	}

	public long getStartTime() {
		return (long) data[0];
	}

	public boolean getIsTriggered() {
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
}
