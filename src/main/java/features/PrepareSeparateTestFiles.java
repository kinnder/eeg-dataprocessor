package features;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import domain.Indication;
import domain.IndicationsFile;
import domain.Sample;
import domain.SamplesFile;
import utility.StringDataFileWriter;

public class PrepareSeparateTestFiles {
	public void action() {
		final String path = "data//";
		final String indicationsFileName = path + "bnd.txt";
		final String samplesFileName = path + "BNDmetki.txt";
		final String folderName = path + "bnd";

		final long signalTime = 300;
		final long triggerTime_average = 450;
		final long interval_left = 204;
		final long interval_right = 396;
		final long duration_average = 1984;

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
					if (sampleTime_end > sample.getStartTime() + duration_average) {
						sampleTime_end = sample.getStartTime() + duration_average;
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
		PrepareSeparateTestFiles feature = new PrepareSeparateTestFiles();
		feature.action();
	}
}
