package application;

public class ApplicationData {

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

	private long signalTime = 300;

	public long getSignalTime() {
		return signalTime;
	}

	public void setSignalTime(long value) {
		signalTime = value;
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
}
