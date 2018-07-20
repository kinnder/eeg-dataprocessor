package domain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import utility.StringDataFileReader;

public class SamplesFile extends StringDataFileReader {

	private long sampleNumber = 1;

	public SamplesFile(BufferedReader bufferedReader) {
		super(bufferedReader);
	}

	public SamplesFile(String fileName) throws FileNotFoundException {
		super(fileName);
	}

	public Sample nextSample() throws IOException {
		Sample s = new Sample(sampleNumber, next());
		sampleNumber++;
		return s;
	}
}
