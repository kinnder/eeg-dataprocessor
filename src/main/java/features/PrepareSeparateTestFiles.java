package features;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import domain.Sample;
import utility.StringDataFileReader;
import utility.StringDataFileWriter;

public class PrepareSeparateTestFiles {

	public void action() {
		final String path = "data//";
		final String indicationsFileName = path + "bnd.txt";
		final String samplesFileName = path + "BNDmetki.txt";
		final String folderName = path + "bnd";
		final int indicationResolutionInMilis = 2;

		File folder = new File(folderName);
		if (!folder.exists()) {
			folder.mkdir();
		}

		try (StringDataFileReader samplesFile = new StringDataFileReader(samplesFileName)) {
			try (StringDataFileReader indicationsFile = new StringDataFileReader(indicationsFileName)) {
				long sampleId = 1;
				long indicationId = 0;

				while (samplesFile.hasNext()) {
					Sample sample = new Sample(samplesFile.next());
					long indicationId_begin = sample.getStartTime() + 204;
					long indicationId_end = indicationId_begin + 204 + 696;

					String testFileName = folderName + "//"
							+ String.format("Tr%04d_%02d.txt", sampleId, sample.getLabel());
					try (StringDataFileWriter testFile = new StringDataFileWriter(testFileName)) {
						while (indicationsFile.hasNext()) {
							String indication = indicationsFile.next();
							indicationId += indicationResolutionInMilis;

							if (indicationId >= indicationId_begin) {
								testFile.writeLine(indication);
							}

							if (indicationId > indicationId_end) {
								break;
							}
						}
					}
					sampleId++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
