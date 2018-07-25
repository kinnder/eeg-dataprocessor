package feature;

import java.util.ArrayList;
import java.util.List;

import event.FeatureStatus;
import event.FeatureStatusListener;

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

	@Override
	public void run() {
	}
}
