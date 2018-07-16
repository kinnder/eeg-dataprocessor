package domain;

import java.util.Arrays;

public class Sample {

	double[] data;

	public Sample(String data) {
		String array[] = splitString(data);
		this.data = Arrays.stream(array).mapToDouble(Double::parseDouble).toArray();
	}

	public long getStartTime() {
		return (long) data[0];
	}

	public boolean getHasTriggered() {
		return getTriggerTime() > 0;
	}

	public int getLabelId() {
		return (int) data[1];
	}

	public long getTriggerTime() {
		int triggerId = getLabelId();
		return (long) data[1 + triggerId];
	}

	private String[] splitString(String data) {
		return data.split(",");
	}
}
