package features;

import application.ApplicationData;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import domain.Indication;
import domain.IndicationsFile;
import domain.Sample;
import domain.SamplesFile;
import utility.StringDataFileWriter;

public class PrepareSeparateTestFiles {

	final String indicationsFileName;

	final String samplesFileName;

	final String folderName;

	final long signalTime;

	final long triggerTime_average;

	final long interval_left;

	final long interval_right;

	final long duration_min;

	public PrepareSeparateTestFiles(ApplicationData data) {
		indicationsFileName = data.getIndicationsFileName();
		samplesFileName = data.getSamplesFileName();
		folderName = data.getOutputFolder();
		signalTime = data.getSignalTime();
		triggerTime_average = data.getTriggerTimeAverage();
		interval_left = data.getIntervalLeft();
		interval_right = data.getIntervalRight();
		duration_min = data.getDurationMin();
	}

	public void action() {
		File folder = new File(folderName);
		if (!folder.exists()) {
			folder.mkdir();
		}

		try (SamplesFile samplesFile = new SamplesFile(samplesFileName)) {
			try (IndicationsFile indicationsFile = new IndicationsFile(indicationsFileName)) {
				while (samplesFile.hasNext()) {
					Sample sample = samplesFile.nextSample();
					long sampleTime_begin = sample.getStartTime() + signalTime;
					if (sample.hasTriggerTime()) {
						sampleTime_begin += sample.getTriggerTime();
					} else {
						sampleTime_begin += triggerTime_average;
					}
					sampleTime_begin -= interval_left;
					long sampleTime_end = sampleTime_begin + interval_left + interval_right;
					if (sampleTime_end > sample.getStartTime() + duration_min) {
						sampleTime_end = sample.getStartTime() + duration_min;
					}

					String testFileName = folderName + "//"
							+ String.format("Tr%04d_%02d.txt", sample.getNumber(), sample.getLabel());
					try (StringDataFileWriter testFile = new StringDataFileWriter(testFileName)) {
						while (indicationsFile.hasNext()) {
							Indication indication = indicationsFile.nextIndication();

							if (indication.getTime() > sampleTime_end) {
								break;
							}

							if (indication.getTime() >= sampleTime_begin) {
								testFile.writeLine(indication.toString());
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

		PrepareSeparateTestFiles feature = new PrepareSeparateTestFiles(applicationData);
		feature.action();
	}
}
