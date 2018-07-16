package utility;

import java.io.BufferedWriter;
import java.io.IOException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringDataFileWriterTest {
	StringDataFileWriter out;

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	BufferedWriter bufferedWriter_mock;

	@Before
	public void setUp() {
		bufferedWriter_mock = context.mock(BufferedWriter.class);

		out = new StringDataFileWriter(bufferedWriter_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void testcase_1() throws IOException {
		context.checking(new Expectations() {
			{
				oneOf(bufferedWriter_mock).close();
			}
		});

		out.close();
	}

	@Test
	public void testcase_2() throws IOException {
		final String s1 = "string 1";

		context.checking(new Expectations() {
			{
				oneOf(bufferedWriter_mock).write(s1);

				oneOf(bufferedWriter_mock).newLine();
			}
		});

		out.writeLine(s1);
	}
}
