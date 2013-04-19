package serie08;

import org.junit.Test;

import utils.TestUtils;

public class MainTest {
	@Test
	public void easy() throws Exception {
		TestUtils.executeTest("public.in", "public.out", MainTest.class, Main.class);
	}
}
