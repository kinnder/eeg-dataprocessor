package feature;

import java.io.FileNotFoundException;
import java.io.IOException;

import application.ApplicationData;
import domain.Sample;
import domain.SamplesFile;

public class AnalyzeSamplesFile extends Feature {

	public AnalyzeSamplesFile(ApplicationData applicationData) {
		super(applicationData);
	}

	@Override
	public void run() {
		notifyFeatureStarted();

		String samplesFileName = applicationData.getSamplesFileName();
		long stimulusTime = applicationData.getStimulusTime();

		long duration_longest = Long.MIN_VALUE;
		long duration_shortest = Long.MAX_VALUE;
		long interval_right = Long.MAX_VALUE;
		long interval_left = Long.MAX_VALUE;
		long triggerTime_shortest = Long.MAX_VALUE;
		long triggerTime_longest = Long.MIN_VALUE;
		long triggerTime_average = 0;
		long samplesAmount_total = 0;
		long samplesAmount_triggered = 0;

		Sample sample_previous = null;
		try (SamplesFile samplesFile = new SamplesFile(samplesFileName)) {
			while (samplesFile.hasNext()) {
				Sample sample_current = samplesFile.nextSample();
				samplesAmount_total++;

				if (sample_current.hasTriggerTime()) {
					long triggerTime = sample_current.getTriggerTime();
					if (triggerTime > triggerTime_longest) {
						triggerTime_longest = triggerTime;
					} else if (triggerTime < triggerTime_shortest) {
						triggerTime_shortest = triggerTime;
					}
					samplesAmount_triggered++;
					triggerTime_average += triggerTime;
				}

				if (sample_previous != null) {
					long duration = sample_current.getStartTime() - sample_previous.getStartTime();
					if (duration > duration_longest) {
						duration_longest = duration;
					} else if (duration < duration_shortest) {
						duration_shortest = duration;
					}
				}
				sample_previous = sample_current;

				notifyFeatureUpdated();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		triggerTime_average /= samplesAmount_triggered;
		interval_left = triggerTime_shortest + stimulusTime;
		interval_right = duration_shortest - triggerTime_longest - stimulusTime;

		applicationData.setTriggerTimeMin(triggerTime_shortest);
		applicationData.setTriggerTimeMax(triggerTime_longest);
		applicationData.setTriggerTimeAverage(triggerTime_average);
		applicationData.setIntervalLeft(interval_left);
		applicationData.setIntervalRight(interval_right);
		applicationData.setDurationMin(duration_shortest);
		applicationData.setDurationMax(duration_longest);
		applicationData.setSamplesAmountTotal(samplesAmount_total);
		applicationData.setSamplesAmountTriggered(samplesAmount_triggered);

		notifyFeatureCompleted();
	}

	public static void main(String args[]) {
		ApplicationData applicationData = ApplicationData.createDefault();
		AnalyzeSamplesFile feature = new AnalyzeSamplesFile(applicationData);
		feature.run();

		System.out.println("Duration-shortest: " + applicationData.getDurationMin());
		System.out.println("Duration-longest : " + applicationData.getDurationMax());
		System.out.println("Interval on the left-shortest : " + applicationData.getIntervalLeft());
		System.out.println("Interval on the right-shortest: " + applicationData.getIntervalRight());
		System.out.println("Trigger-time shortest: " + applicationData.getTriggerTimeMin());
		System.out.println("Trigger-time longest : " + applicationData.getTriggerTimeMax());
		System.out.println("Trigger-time average : " + applicationData.getTriggerTimeAverage());
		System.out.println("Samples-amount total    : " + applicationData.getSamplesAmountTotal());
		System.out.println("Samples-amount triggered: " + applicationData.getSamplesAmountTriggered());
	}
}
