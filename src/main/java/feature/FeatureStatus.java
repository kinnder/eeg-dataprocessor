package feature;

public class FeatureStatus {

	public static final int STARTED = 1;
	public static final int COMPLETED = 2;
	public static final int UPDATED = 3;

	private int type;

	public int getType() {
		return type;
	}

	public FeatureStatus(int type) {
		this.type = type;
	}
}
