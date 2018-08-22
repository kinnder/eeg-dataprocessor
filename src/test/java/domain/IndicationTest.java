package domain;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IndicationTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	private static final double DELTA = 0.005;

	Indication out;

	@Before
	public void setUp() {
		out = new Indication();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void testcase_2() {
		final double data[] = { -2222.79, -1641.97, -459.69, -930.11, -9722.95, -8599.22, -7867.01, -145.69, -7021.75,
				-16960.15, -10062.29, -9247.15, -6461.45, -7487.53, -8941.25, -7231.84, -7245.65, -6049.47, -5008.15,
				-7208.51, -6426.87, -4344.89, -7091.94, 148.60, -4845.21, -6641.17, -2078.32, -3431.09, -1181.22,
				-797.25, 1150.40, -14368.31 };
		final String string = " -2222.79  -1641.97   -459.69   -930.11  -9722.95  -8599.22  -7867.01   -145.69  -7021.75 -16960.15 -10062.29  -9247.15  -6461.45  -7487.53  -8941.25  -7231.84  -7245.65  -6049.47  -5008.15  -7208.51  -6426.87  -4344.89  -7091.94    148.60  -4845.21  -6641.17  -2078.32  -3431.09  -1181.22   -797.25   1150.40 -14368.31 ";

		out = new Indication(string);

		Assert.assertArrayEquals(data, out.getData(), DELTA);
	}

	@Test
	public void testcase_1() {
		final double data[] = { -2590.77, -1910.83, -524.54, -1084.89, -11416.04, -10086.00, -9233.32, -152.03,
				-8235.75, -19915.06, -11808.68, -10856.90, -7578.15, -8786.46, -10490.52, -8484.83, -8503.01, -7095.65,
				-5872.01, -8451.53, -7537.98, -5088.99, -8318.71, 186.74, -5675.52, -7790.92, -2426.67, -4015.03,
				-1371.36, -919.56, 1369.43, -13363.30 };
		final String string = " -2590.77  -1910.83   -524.54  -1084.89 -11416.04 -10086.00  -9233.32   -152.03  -8235.75 -19915.06 -11808.68 -10856.90  -7578.15  -8786.46 -10490.52  -8484.83  -8503.01  -7095.65  -5872.01  -8451.53  -7537.98  -5088.99  -8318.71    186.74  -5675.52  -7790.92  -2426.67  -4015.03  -1371.36   -919.56   1369.43 -13363.30 ";

		out = new Indication(data);

		Assert.assertEquals(string, out.toString());
	}

	@Test
	public void testcase_11() {
		final double data[] = { -2590.77, -1910.83, -524.54, -1084.89, -11416.04 };
		final String string = " -2590.77  -1910.83   -524.54  -1084.89 -11416.04 ";

		out = new Indication(data);

		Assert.assertEquals(string, out.toString());
	}

	@Test
	public void testcase_3() {
		Assert.assertEquals(Indication.NO_TIME, out.getTime());
	}

	@Test
	public void testcase_4() {
		final double data[] = { -2590.77, -1910.83, -524.54, -1084.89, -11416.04, -10086.00, -9233.32, -152.03,
				-8235.75, -19915.06, -11808.68, -10856.90, -7578.15, -8786.46, -10490.52, -8484.83, -8503.01, -7095.65,
				-5872.01, -8451.53, -7537.98, -5088.99, -8318.71, 186.74, -5675.52, -7790.92, -2426.67, -4015.03,
				-1371.36, -919.56, 1369.43, -13363.30 };

		out = new Indication(data);

		Assert.assertEquals(32, out.getChannels());
		Assert.assertEquals(-2590.77, out.getChannel(0), DELTA);
		Assert.assertEquals(-13363.30, out.getChannel(31), DELTA);
	}
}
