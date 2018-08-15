package application;

import domain.IndicationsFile;

public class ApplicationData {

	public static ApplicationData createDefault() {
		ApplicationData applicationData = new ApplicationData();
		applicationData.setSamplesFileName("data//BNDmetki.txt");
		applicationData.setIndicationsFileName("data//bnd.txt");
		applicationData.setIndicationsFileTimeStep(IndicationsFile.timeStep_500HZ);
		applicationData.setOutputFolder("data//bnd");
		applicationData.setStimulusTime(300);
		applicationData.setTriggerTimeAverage(450);
		applicationData.setIntervalLeft(504);
		applicationData.setIntervalRight(988);
		applicationData.setDurationMin(1984);
		return applicationData;
	}

	private String samplesFileName = "";

	public String getSamplesFileName() {
		return samplesFileName;
	}

	public void setSamplesFileName(String text) {
		samplesFileName = text;
	}

	private String indicationsFileName = "";

	public String getIndicationsFileName() {
		return indicationsFileName;
	}

	public void setIndicationsFileName(String text) {
		indicationsFileName = text;
	}

	private String outputFolder = "";

	public String getOutputFolder() {
		return outputFolder;
	}

	public void setOutputFolder(String text) {
		outputFolder = text;
	}

	private long stimulusTime = 300;

	public long getStimulusTime() {
		return stimulusTime;
	}

	public void setStimulusTime(long value) {
		stimulusTime = value;
	}

	private long triggerTime_average = 450;

	public long getTriggerTimeAverage() {
		return triggerTime_average;
	}

	public void setTriggerTimeAverage(long value) {
		triggerTime_average = value;
	}

	private long triggerTime_min;

	public void setTriggerTimeMin(long value) {
		triggerTime_min = value;
	}

	public long getTriggerTimeMin() {
		return triggerTime_min;
	}

	private long triggerTime_max;

	public void setTriggerTimeMax(long value) {
		triggerTime_max = value;
	}

	public long getTriggerTimeMax() {
		return triggerTime_max;
	}

	private long interval_left = 204;

	public long getIntervalLeft() {
		return interval_left;
	}

	public void setIntervalLeft(long value) {
		interval_left = value;
	}

	private long interval_right = 396;

	public long getIntervalRight() {
		return interval_right;
	}

	public void setIntervalRight(long value) {
		interval_right = value;
	}

	private long duration_min = 1984;

	public long getDurationMin() {
		return duration_min;
	}

	public void setDurationMin(long value) {
		duration_min = value;
	}

	private long duration_max;

	public void setDurationMax(long value) {
		duration_max = value;
	}

	public long getDurationMax() {
		return duration_max;
	}

	private long samplesAmount_total;

	public void setSamplesAmountTotal(long value) {
		samplesAmount_total = value;
	}

	public long getSamplesAmountTotal() {
		return samplesAmount_total;
	}

	private long samplesAmount_triggered;

	public void setSamplesAmountTriggered(long value) {
		samplesAmount_triggered = value;
	}

	public long getSamplesAmountTriggered() {
		return samplesAmount_triggered;
	}

	private long indicationsFileTimeStep = IndicationsFile.timeStep_500HZ;

	public long getIndicationsFileTimeStep() {
		return indicationsFileTimeStep;
	}

	public void setIndicationsFileTimeStep(long value) {
		indicationsFileTimeStep = value;
	}

	public Parameters getParameters() {
		Parameters parameters = new Parameters();
		parameters.setStimulusTime(stimulusTime);
		parameters.setIntervalLeft(interval_left);
		parameters.setIntervalRight(interval_right);
		parameters.setDurationMin(duration_min);
		parameters.setTriggerTimeAverage(triggerTime_average);
		parameters.setIndicationsFileTimeStep(indicationsFileTimeStep);
		return parameters;
	}

	public void setParameters(Parameters parameters) {
		stimulusTime = parameters.getStimulusTime();
		interval_left = parameters.getIntervalLeft();
		interval_right = parameters.getIntervalRight();
		duration_min = parameters.getDurationMin();
		triggerTime_average = parameters.getTriggerTimeAverage();
		indicationsFileTimeStep = parameters.getIndicationsFileTimeStep();
	}
}
