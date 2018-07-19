package domain;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class SampleTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	Sample out;

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void testcase_1() {
		String data = "  409596,       1,     696,       0,       0,       0,       1,       0,       0,       0,";

		out = new Sample(data);

		Assert.assertEquals(409596, out.getStartTime());
		Assert.assertEquals(true, out.getIsTriggered());
		Assert.assertEquals(1, out.getLabel());
		Assert.assertEquals(696, out.getTriggerTime());
	}

	@Test
	public void testcase_2() {
		String data = "  411610,       2,       0,     494,       0,       0,       0,       1,       0,       0,";

		out = new Sample(data);

		Assert.assertEquals(411610, out.getStartTime());
		Assert.assertEquals(true, out.getIsTriggered());
		Assert.assertEquals(2, out.getLabel());
		Assert.assertEquals(494, out.getTriggerTime());
	}

	@Test
	public void testcase_3() {
		String data = "  395572,       1,       0,       0,       0,       0,       0,       0,       0,       0,";

		out = new Sample(data);

		Assert.assertEquals(395572, out.getStartTime());
		Assert.assertEquals(false, out.getIsTriggered());
		Assert.assertEquals(1, out.getLabel());
		Assert.assertEquals(0, out.getTriggerTime());
	}
}
