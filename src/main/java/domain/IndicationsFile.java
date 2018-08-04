package domain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import utility.StringDataFileReader;

public class IndicationsFile extends StringDataFileReader {

	private long time = 0;

	private long timeStep;

	public static long timeStep_500HZ = 2;

	public static long timeStep_250HZ = 4;

	public IndicationsFile(BufferedReader bufferedReader) {
		super(bufferedReader);
	}

	public IndicationsFile(String fileName, long timeStep) throws FileNotFoundException {
		super(fileName);
		this.timeStep = timeStep;
	}

	public Indication nextIndication() throws IOException {
		Indication i = new Indication(time, next());
		time += timeStep;
		return i;
	}
}
