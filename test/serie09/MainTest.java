package serie09;

import org.junit.Test;

import utils.TestUtils;

public class MainTest {
	@Test
	public void small() throws Exception {
		TestUtils.executeTest("small.in", "small.out", MainTest.class, Main.class);
	}

	@Test
	public void medium() throws Exception {
		TestUtils.executeTest("medium.in", "medium.out", MainTest.class, Main.class);
	}

	@Test
	public void large() throws Exception {
		TestUtils.executeTest("large.in", "large.out", MainTest.class, Main.class);
	}
}
