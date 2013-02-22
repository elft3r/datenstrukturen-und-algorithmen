package serie01;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import utils.TestUtils;

public class MainTest {
	@Test
	public void easy() throws FileNotFoundException {
		executeTest("easy.in", "easy.out");
	}

	@Test
	public void medium() throws FileNotFoundException {
		executeTest("medium.in", "medium.out");
	}

	@Test
	public void hard() throws FileNotFoundException {
		executeTest("hard.in", "hard.out");
	}
	
	private void executeTest(String inFile, String outFile) throws FileNotFoundException {
		InputStream isIn = TestUtils.getInputStream(inFile, MainTest.class);
		List<Long> res = Main.doIt(isIn);
		assertResult(outFile, res);
	}

	private void assertResult(String filename, List<Long> res) throws FileNotFoundException {
		List<String> expectedRes = TestUtils.getFileContent(filename, MainTest.class);
		
		assertEquals("Should be the same number of results!", expectedRes.size(), res.size());
		for(int i = 0; i < res.size(); i++)
			assertEquals("Result in line " + i + " are not the same!", expectedRes.get(i), res.get(i).toString());
	}
}
