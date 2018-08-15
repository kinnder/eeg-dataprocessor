package feature;

import application.ApplicationData;

public class PrepareSingleLabelFiles extends Feature {

	public PrepareSingleLabelFiles(ApplicationData applicationData) {
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
		PrepareSingleLabelFiles feature = new PrepareSingleLabelFiles(applicationData);
		feature.run();
	}
}
