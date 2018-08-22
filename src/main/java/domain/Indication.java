package domain;

import java.util.Locale;

public class Indication {

	final static long NO_TIME = -1;

	double[] data;

	private String dataAsString;

	public Indication(double[] data) {
		this.data = data;
	}

	public Indication(String data) {
		this.dataAsString = data;
	}

	public Indication(long time, String data) {
		this(data);
		this.time = time;
	}

	public Indication() {
	}

	public String toString() {
		if (dataAsString != null) {
			return dataAsString;
		}
		dataAsString = "";
		if (data != null) {
			for (double value : data) {
				dataAsString += String.format(Locale.US, "% 9.02f ", value);
			}
		}
		return dataAsString;
	}

	private long time = NO_TIME;

	public long getTime() {
		return time;
	}

	private String[] splitString(String data) {
		return data.trim().split("\\s+");
	}

	private void parseData() {
		String[] split = splitString(dataAsString);
		int length = split.length;
		data = new double[length];
		for (int i = 0; i < length; i++) {
			data[i] = Double.parseDouble(split[i]);
		}
	}

	public int getChannels() {
		if (data == null && dataAsString != null) {
			parseData();
		}
		if (data != null) {
			return data.length;
		}
		return 0;
	}

	public double[] getData() {
		if (data == null && dataAsString != null) {
			parseData();
		}
		if (data != null) {
			return data;
		}
		return null;
	}

	public double getChannel(int idx) {
		if (data == null && dataAsString != null) {
			parseData();
		}
		if (data != null) {
			return data[idx];
		}
		return 0;
	}
}
