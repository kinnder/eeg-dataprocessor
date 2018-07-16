package utility;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;

public class StringDataFileWriter implements Closeable {
	private BufferedWriter bufferedWriter;

	public StringDataFileWriter(BufferedWriter bufferedWriter) {
		this.bufferedWriter = bufferedWriter;
	}

	public StringDataFileWriter(String fileName) throws IOException {
		bufferedWriter = new BufferedWriter(new FileWriter(fileName));
	}

	@Override
	public void close() throws IOException {
		if (bufferedWriter != null) {
			bufferedWriter.close();
		}
	}

	public void writeLine(String data) throws IOException {
		bufferedWriter.write(data);
		bufferedWriter.newLine();
	}
}
