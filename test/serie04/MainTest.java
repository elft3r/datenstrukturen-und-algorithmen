package serie04;

import org.junit.Test;

import utils.TestUtils;

public class MainTest {
	@Test
	public void publicTest() throws Exception {
		TestUtils.executeTest("public.in", "public.out", MainTest.class, Main.class);
	}
}