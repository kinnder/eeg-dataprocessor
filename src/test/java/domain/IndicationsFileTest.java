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

public class IndicationsFileTest {

	IndicationsFile out;

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	BufferedReader bufferedReader_mock;

	@Before
	public void setUp() {
		bufferedReader_mock = context.mock(BufferedReader.class);

		out = new IndicationsFile(bufferedReader_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void testcase_1() throws IOException {
		final String s1 = " -2222.79  -1641.97   -459.69   -930.11  -9722.95  -8599.22  -7867.01   -145.69  -7021.75 -16960.15 -10062.29  -9247.15  -6461.45  -7487.53  -8941.25  -7231.84  -7245.65  -6049.47  -5008.15  -7208.51  -6426.87  -4344.89  -7091.94    148.60  -4845.21  -6641.17  -2078.32  -3431.09  -1181.22   -797.25   1150.40 -14368.31 ";
		final String s2 = " -2590.77  -1910.83   -524.54  -1084.89 -11416.04 -10086.00  -9233.32   -152.03  -8235.75 -19915.06 -11808.68 -10856.90  -7578.15  -8786.46 -10490.52  -8484.83  -8503.01  -7095.65  -5872.01  -8451.53  -7537.98  -5088.99  -8318.71    186.74  -5675.52  -7790.92  -2426.67  -4015.03  -1371.36   -919.56   1369.43 -13363.30 ";

		context.checking(new Expectations() {
			{
				oneOf(bufferedReader_mock).readLine();
				will(returnValue(s1));

				oneOf(bufferedReader_mock).readLine();
				will(returnValue(s2));
			}
		});

		Indication i = out.nextIndication();
		Assert.assertNotNull(i);
		Assert.assertEquals(0, i.getTime());

		i = out.nextIndication();
		Assert.assertNotNull(i);
		Assert.assertEquals(2, i.getTime());
	}
}
