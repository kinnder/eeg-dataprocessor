package feature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import application.ApplicationData;
import domain.Indication;
import domain.IndicationsFile;
import domain.Sample;
import domain.SamplesFile;
import utility.StringDataFileWriter;

public class PrepareChannelInOneRowFile extends Feature {

	public PrepareChannelInOneRowFile(ApplicationData applicationData) {
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
					if (!sample.hasTriggerTime()) {
						continue;
					}
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

					long indicationsAmount = intervalTime_end - intervalTime_begin;
					Indication indication[] = new Indication[(int) indicationsAmount];

					for (int i = 0; i < indicationsAmount; i++) {
						if (indicationsFile.hasNext()) {
							indication[i] = indicationsFile.nextIndication();
							if (indication[i].getTime() > intervalTime_end) {
								break;
							}
						} else {
							indication[i] = new Indication();
						}
					}
					int channelsAmount = indication[0].getChannels();

					StringDataFileWriter testFile = selectTestFile(sample.getLabel());
					for (int j = 0; j < channelsAmount; j++) {
						for (int i = 0; i < indicationsAmount; i++) {
							double value = indication[i].getChannel(j);
							testFile.write(String.format(Locale.US, "% 9.02f ", value));
						}
						testFile.newLine();
					}
					testFile.newLine();

					notifyFeatureUpdated();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (StringDataFileWriter testFile : testFiles.values()) {
			try {
				testFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		notifyFeatureCompleted();
	}

	Map<Integer, StringDataFileWriter> testFiles = new HashMap<Integer, StringDataFileWriter>();

	private StringDataFileWriter selectTestFile(int label) throws IOException {
		StringDataFileWriter testFile = testFiles.get(label);
		if (testFile == null) {
			String folderName = applicationData.getOutputFolder();
			String testFileName = folderName + "//" + String.format("file4_%02d.txt", label);
			testFile = new StringDataFileWriter(testFileName);
			testFiles.put(label, testFile);
		}
		return testFile;
	}

	public static void main(String args[]) {
		ApplicationData applicationData = ApplicationData.createDefault();
		Feature feature = new PrepareChannelInOneRowFile(applicationData);
		feature.run();
	}
}
