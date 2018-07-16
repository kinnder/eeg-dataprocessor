package features;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import domain.Sample;

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

		try (BufferedReader samplesFile = new BufferedReader(new FileReader(samplesFileName))) {
			try (BufferedReader indicationsFile = new BufferedReader(new FileReader(indicationsFileName))) {
				long sampleId = 1;
				long indicationId = 395570;

				String sampleAsString;
				if ((sampleAsString = samplesFile.readLine()) == null) {
					System.out.println("no samples");
					return;
				}
				Sample sample_current = new Sample(sampleAsString);

				READING_SAMPLES: while (true) {
					Sample sample_next = null;
					if ((sampleAsString = samplesFile.readLine()) != null) {
						sample_next = new Sample(sampleAsString);
					}
//					long indicationId_begin = sample_current.getStartTime();
					long indicationId_begin = sample_current.getStartTime() + 204;
					long indicationId_end = indicationId_begin + 900;
//					long indicationId_end = Long.MAX_VALUE;
//					if (sample_next != null) {
//						indicationId_end = sample_next.getStartTime();
//					}

					String testFileName = folderName + "//"
							+ String.format("Tr%d_%d.txt", sampleId, sample_current.getLabelId());
					try (BufferedWriter testFile = new BufferedWriter(new FileWriter(testFileName))) {
						String indicationAsString;
						READING_INDICATIONS: while (true) {
							indicationAsString = indicationsFile.readLine();
							if (indicationAsString == null) {
								break READING_INDICATIONS;
							}
							indicationId += indicationResolutionInMilis;

							if (indicationId >= indicationId_begin) {
								testFile.write(indicationAsString);
								testFile.newLine();
							}

							if (indicationId > indicationId_end) {
								break READING_INDICATIONS;
							}
						}
					}
					if (sample_next == null) {
						break READING_SAMPLES;
					}
					sample_current = sample_next;
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
