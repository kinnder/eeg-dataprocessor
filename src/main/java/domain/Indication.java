package domain;

public class Indication {

	double[] data;

	private String dataAsString;

	public Indication(double[] data) {
		this.data = data;
	}

	public Indication(String data) {
		this.dataAsString = data;
	}

	public String toString() {
		return dataAsString;
	}

	private String[] splitString(String data) {
		return data.trim().split("\\s+");
	}

	public int getChannels(String data) {
		return splitString(data).length;
	}

	public Indication construct(String data) {
		String[] split = splitString(data);
		int length = split.length;
		double[] doubles = new double[length];
		for (int i = 0; i < length; i++) {
			doubles[i] = Double.parseDouble(split[i]);
		}
		return new Indication(doubles);
	}

	public int getChannels() {
		return 0;
	}

	public double[] getData() {
		// TODO Auto-generated method stub
		return null;
	}
}
