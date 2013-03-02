package serie02;

import org.junit.Test;

import utils.TestUtils;

public class MainTest {
	@Test
	public void easy() throws Exception {
		TestUtils.executeTest("easy.in", "easy.out", MainTest.class, Main.class);
	}

	@Test
	public void medium() throws Exception {
		TestUtils.executeTest("medium.in", "medium.out", MainTest.class, Main.class);
	}

	@Test
	public void hard() throws Exception {
		TestUtils.executeTest("hard.in", "hard.out", MainTest.class, Main.class);
	}
}