package feature;

import application.ApplicationData;

public class PrepareChannelInOneRowFile extends Feature {

	public PrepareChannelInOneRowFile(ApplicationData applicationData) {
		super(applicationData);
	}

	@Override
	public void run() {
		notifyFeatureStarted();
		notifyFeatureUpdated();
		notifyFeatureCompleted();
	}

	public static void main(String args[]) {
		ApplicationData applicationData = ApplicationData.createDefault();
		Feature feature = new PrepareChannelInOneRowFile(applicationData);
		feature.run();
	}
}
