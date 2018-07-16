package features;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import domain.Sample;
import utility.StringDataFileReader;

/*
 * Заменить FileReader
 * BufferedReader java.nio.file.Files.newBufferedReader(Path path, Charset cs) throws IOException
 */

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
				long indicationId = 395570;

				while (samplesFile.hasNext()) {
					Sample sample = new Sample(samplesFile.next());
					long indicationId_begin = sample.getStartTime() + 204;
					long indicationId_end = indicationId_begin + 204 + 696;

					String testFileName = folderName + "//"
							+ String.format("Tr%04d_%02d.txt", sampleId, sample.getLabelId());
					try (BufferedWriter testFile = new BufferedWriter(new FileWriter(testFileName))) {
						String indicationAsString;
						while (indicationsFile.hasNext()) {
							indicationAsString = indicationsFile.next();
							indicationId += indicationResolutionInMilis;

							if (indicationId >= indicationId_begin) {
								testFile.write(indicationAsString);
								testFile.newLine();
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
