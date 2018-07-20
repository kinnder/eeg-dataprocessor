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

		File folder = new File(folderName);
		if (!folder.exists()) {
			folder.mkdir();
		}

		try (SamplesFile samplesFile = new SamplesFile(samplesFileName)) {
			try (IndicationsFile indicationsFile = new IndicationsFile(indicationsFileName)) {
				while (samplesFile.hasNext()) {
					Sample sample = samplesFile.nextSample();
					long sampleTime_begin = sample.getStartTime() + 204;
					long sampleTime_end = sampleTime_begin + 204 + 696;

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
}
