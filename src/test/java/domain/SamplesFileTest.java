package domain;

import java.io.BufferedReader;
import java.io.IOException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SamplesFileTest {

	SamplesFile out;

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	BufferedReader bufferedReader_mock;

	@Before
	public void setUp() {
		bufferedReader_mock = context.mock(BufferedReader.class);

		out = new SamplesFile(bufferedReader_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void testcase_1() throws IOException {
		final String s1 = "  409596,       1,     696,       0,       0,       0,       1,       0,       0,       0,";
		final String s2 = "  411610,       2,       0,     494,       0,       0,       0,       1,       0,       0,";

		context.checking(new Expectations() {
			{
				oneOf(bufferedReader_mock).readLine();
				will(returnValue(s1));

				oneOf(bufferedReader_mock).readLine();
				will(returnValue(s2));
			}
		});

		Sample sample = out.nextSample();
		Assert.assertNotNull(sample);
		Assert.assertEquals(1, sample.getNumber());

		sample = out.nextSample();
		Assert.assertNotNull(sample);
		Assert.assertEquals(2, sample.getNumber());
	}
}
