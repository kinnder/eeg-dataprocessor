package utility;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

public class StringDataFileReader implements Closeable {
	private BufferedReader bufferedReader;

	public StringDataFileReader(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

	public StringDataFileReader(String fileName) throws FileNotFoundException {
		bufferedReader = new BufferedReader(new FileReader(fileName));
	}

	@Override
	public void close() throws IOException {
		if (bufferedReader != null) {
			bufferedReader.close();
		}
	}

	private String bufferedData;

	private boolean endOfFile;

	public boolean hasNext() throws IOException {
		updateBufferedData();
		return !endOfFile;
	}

	public String next() throws IOException {
		updateBufferedData();
		if (endOfFile) {
			throw new NoSuchElementException();
		}
		String result = bufferedData;
		bufferedData = null;
		return result;
	}

	private void updateBufferedData() throws IOException {
		if (endOfFile) {
			return;
		}
		if (bufferedData == null) {
			bufferedData = bufferedReader.readLine();
		}
		endOfFile = bufferedData == null;
	}
}
