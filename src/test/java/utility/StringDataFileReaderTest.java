package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringDataFileReaderTest {
	StringDataFileReader out;

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	BufferedReader bufferedReader_mock;

	@Before
	public void setUp() {
		bufferedReader_mock = context.mock(BufferedReader.class);

		out = new StringDataFileReader(bufferedReader_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void testcase_1() throws IOException {
		context.checking(new Expectations() {
			{
				oneOf(bufferedReader_mock).close();
			}
		});

		out.close();
	}

	@Test
	public void testcase_2() throws IOException {
		final String s1 = "string 1";

		context.checking(new Expectations() {
			{
				oneOf(bufferedReader_mock).readLine();
				will(returnValue(s1));
			}
		});

		Assert.assertTrue(out.hasNext());
		Assert.assertEquals(s1, out.next());
	}

	@Test(expected = NoSuchElementException.class)
	public void testcase_3() throws IOException {
		context.checking(new Expectations() {
			{
				oneOf(bufferedReader_mock).readLine();
				will(returnValue(null));
			}
		});

		Assert.assertFalse(out.hasNext());
		out.next();
	}

	@Test
	public void testcase_4() throws IOException {
		final String s1 = "string 1";
		final String s2 = "string 2";

		context.checking(new Expectations() {
			{
				oneOf(bufferedReader_mock).readLine();
				will(returnValue(s1));

				oneOf(bufferedReader_mock).readLine();
				will(returnValue(s2));
			}
		});

		Assert.assertEquals(s1, out.next());
		Assert.assertEquals(s2, out.next());
	}

	@Test
	public void testcase_5() throws IOException {
		final String s1 = "string 1";

		context.checking(new Expectations() {
			{
				oneOf(bufferedReader_mock).readLine();
				will(returnValue(s1));
			}
		});

		Assert.assertTrue(out.hasNext());
		Assert.assertTrue(out.hasNext());
		Assert.assertEquals(s1, out.next());
	}
}
