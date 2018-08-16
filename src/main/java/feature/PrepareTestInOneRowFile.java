package feature;

import application.ApplicationData;

public class PrepareTestInOneRowFile extends Feature {

	public PrepareTestInOneRowFile(ApplicationData applicationData) {
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
		Feature feature = new PrepareTestInOneRowFile(applicationData);
		feature.run();
	} 
}
