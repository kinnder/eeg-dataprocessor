package feature;

import java.util.ArrayList;
import java.util.List;

import application.ApplicationData;

public class Feature implements Runnable {

	private List<FeatureStatusListener> featureStatusListeners = new ArrayList<FeatureStatusListener>();

	public void addFeatureStatusListener(FeatureStatusListener listener) {
		if (!featureStatusListeners.contains(listener)) {
			featureStatusListeners.add(listener);
		}
	}

	public void removeFeatureStatusListener(FeatureStatusListener listener) {
		featureStatusListeners.remove(listener);
	}

	public void notifyFeatureStatus(FeatureStatus event) {
		for (FeatureStatusListener listener : featureStatusListeners) {
			listener.notifyFeatureStatus(event);
		}
	}

	protected ApplicationData applicationData;

	public Feature(ApplicationData applicationData) {
		this.applicationData = applicationData;
	}

	@Override
	public void run() {
	}

	public void notifyFeatureStarted() {
		notifyFeatureStatus(new FeatureStatus(FeatureStatus.STARTED));
	}

	public void notifyFeatureUpdated() {
		notifyFeatureStatus(new FeatureStatus(FeatureStatus.UPDATED));
	}

	public void notifyFeatureCompleted() {
		notifyFeatureStatus(new FeatureStatus(FeatureStatus.COMPLETED));
	}
}
