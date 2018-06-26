package domain;

public class IndicationFactory {

	public int getChannels(String data) {
		return splitString(data).length;
	}

	public Indication create(String data) {
		String[] split = splitString(data);
		int length = split.length;
		double[] doubles = new double[length];
		for (int i = 0; i < length; i++) {
			doubles[i] = Double.parseDouble(split[i]);
		}
		return new Indication(doubles);
	}

	private String[] splitString(String data) {
		return data.trim().split("\\s+");
	}
}
