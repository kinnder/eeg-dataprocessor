package domain;

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
		return dataAsString;
	}

	private long time = NO_TIME;

	public long getTime() {
		return time;
	}

	private String[] splitString(String data) {
		return data.trim().split("\\s+");
	}

	public int getChannels(String data) {
		return splitString(data).length;
	}

	@Deprecated
	public Indication construct(String data) {
		String[] split = splitString(data);
		int length = split.length;
		double[] doubles = new double[length];
		for (int i = 0; i < length; i++) {
			doubles[i] = Double.parseDouble(split[i]);
		}
		return new Indication(doubles);
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

	public double getChannel(int j) {
		if (data == null && dataAsString != null) {
			parseData();
		}
		if (data != null) {
			return data[j];
		}
		return 0;
	}
}
