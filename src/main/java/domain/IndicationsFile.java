package domain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import utility.StringDataFileReader;

public class IndicationsFile extends StringDataFileReader {

	private long time = 0;

	private long timeStep = 2;

	public IndicationsFile(BufferedReader bufferedReader) {
		super(bufferedReader);
	}

	public IndicationsFile(String fileName) throws FileNotFoundException {
		super(fileName);
	}

	public Indication nextIndication() throws IOException {
		Indication i = new Indication(time, next());
		time += timeStep;
		return i;
	}
}
