package feature;

import application.ApplicationData;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import domain.Indication;
import domain.IndicationsFile;
import domain.Sample;
import domain.SamplesFile;
import utility.StringDataFileWriter;

public class PrepareSingleTestFiles extends Feature {

	public PrepareSingleTestFiles(ApplicationData applicationData) {
		super(applicationData);
	}

	@Override
	public void run() {
		notifyFeatureStarted();

		String indicationsFileName = applicationData.getIndicationsFileName();
		String samplesFileName = applicationData.getSamplesFileName();
		String folderName = applicationData.getOutputFolder();
		long stimulusTime = applicationData.getStimulusTime();
		long triggerTime_average = applicationData.getTriggerTimeAverage();
		long interval_left = applicationData.getIntervalLeft();
		long interval_right = applicationData.getIntervalRight();
		long duration_min = applicationData.getDurationMin();
		long indicationsFileTimeStep = applicationData.getIndicationsFileTimeStep();

		File folder = new File(folderName);
		if (!folder.exists()) {
			folder.mkdir();
		}

		try (SamplesFile samplesFile = new SamplesFile(samplesFileName)) {
			try (IndicationsFile indicationsFile = new IndicationsFile(indicationsFileName, indicationsFileTimeStep)) {
				while (samplesFile.hasNext()) {
					Sample sample = samplesFile.nextSample();
					long intervalTime_begin = sample.getStartTime() + stimulusTime;
					if (sample.hasTriggerTime()) {
						intervalTime_begin += sample.getTriggerTime();
					} else {
						intervalTime_begin += triggerTime_average;
					}
					intervalTime_begin -= interval_left;
					long intervalTime_end = intervalTime_begin + interval_left + interval_right;
					if (intervalTime_end > sample.getStartTime() + duration_min) {
						intervalTime_end = sample.getStartTime() + duration_min;
					}

					String testFileName = folderName + "//"
							+ String.format("Tr%04d_%02d.txt", sample.getNumber(), sample.getLabel());
					try (StringDataFileWriter testFile = new StringDataFileWriter(testFileName)) {
						while (indicationsFile.hasNext()) {
							Indication indication = indicationsFile.nextIndication();

							if (indication.getTime() > intervalTime_end) {
								break;
							}

							if (indication.getTime() >= intervalTime_begin) {
								testFile.writeLine(indication.toString());
							}
						}
					}

					notifyFeatureUpdated();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		notifyFeatureCompleted();
	}

	public static void main(String args[]) {
		ApplicationData applicationData = ApplicationData.createDefault();
		Feature feature = new PrepareSingleTestFiles(applicationData);
		feature.run();
	}
}
