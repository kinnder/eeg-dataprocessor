package feature;

import java.io.FileNotFoundException;
import java.io.IOException;

import application.ApplicationData;
import domain.Sample;
import domain.SamplesFile;
import event.FeatureStatus;

public class AnalyzeSamplesFile extends Feature {

	final String indicationsFileName;

	final String samplesFileName;

	final String folderName;

	final long signalTime;

	final long triggerTime_average;

	final long interval_left;

	final long interval_right;

	final long duration_min;

	private ApplicationData data;

	public AnalyzeSamplesFile(ApplicationData data) {
		indicationsFileName = data.getIndicationsFileName();
		samplesFileName = data.getSamplesFileName();
		folderName = data.getOutputFolder();
		signalTime = data.getSignalTime();
		triggerTime_average = data.getTriggerTimeAverage();
		interval_left = data.getIntervalLeft();
		interval_right = data.getIntervalRight();
		duration_min = data.getDurationMin();
		this.data = data;
	}

	@Override
	public void run() {
		notifyFeatureStatus(new FeatureStatus(FeatureStatus.STARTED));

		long duration_longest = Long.MIN_VALUE;
		long duration_shortest = Long.MAX_VALUE;
		long interval_right = Long.MAX_VALUE;
		long interval_left = Long.MAX_VALUE;
		long triggerTime_shortest = Long.MAX_VALUE;
		long triggerTime_longest = Long.MIN_VALUE;
		long triggerTime_average = Long.MIN_VALUE;

		Sample sample_previous = null;
		try (SamplesFile samplesFile = new SamplesFile(samplesFileName)) {
			while (samplesFile.hasNext()) {
				Sample sample_current = samplesFile.nextSample();

				if (sample_current.hasTriggerTime()) {
					long triggerTime = sample_current.getTriggerTime() + signalTime;
					if (triggerTime > triggerTime_longest) {
						triggerTime_longest = triggerTime;
					} else if (triggerTime < triggerTime_shortest) {
						triggerTime_shortest = triggerTime;
					}
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
				notifyFeatureStatus(new FeatureStatus(FeatureStatus.UPDATED));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		triggerTime_average = (triggerTime_longest + triggerTime_shortest) / 2;
		interval_left = triggerTime_shortest;
		interval_right = duration_shortest - triggerTime_longest;

		System.out.println("Duration-shortest: " + duration_shortest);
		System.out.println("Duration-longest: " + duration_longest);
		System.out.println("Interval on the left-shortest : " + interval_left);
		System.out.println("Interval on the right-shortest: " + interval_right);
		System.out.println("Trigger-time shortest: " + triggerTime_shortest);
		System.out.println("Trigger-time longest: " + triggerTime_longest);
		System.out.println("Trigger-time average: " + triggerTime_average);

		data.setTriggerTimeAverage(triggerTime_average);
		data.setIntervalLeft(interval_left);
		data.setIntervalRight(interval_right);
		data.setDurationMin(duration_shortest);
		notifyFeatureStatus(new FeatureStatus(FeatureStatus.COMPLETED));
	}

	public static void main(String args[]) {
		ApplicationData applicationData = new ApplicationData();
		applicationData.setSamplesFileName("data//BNDmetki.txt");
		applicationData.setIndicationsFileName("data//bnd.txt");
		applicationData.setOutputFolder("data//bnd");

		applicationData.setSignalTime(300);
		applicationData.setTriggerTimeAverage(450);
		applicationData.setIntervalLeft(204);
		applicationData.setIntervalRight(396);
		applicationData.setDurationMin(1984);

		AnalyzeSamplesFile feature = new AnalyzeSamplesFile(applicationData);
		feature.run();
	}
}
