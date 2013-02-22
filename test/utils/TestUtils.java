package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestUtils {
	private static final String PATH = "test/";
	
	public static InputStream getInputStream(String filename, Class<? extends Object> clazz)
			throws FileNotFoundException {
		return getInputStream(PATH + clazz.getPackage().getName().replace('.', File.separatorChar) + File.separatorChar + filename);
	}

	public static InputStream getInputStream(String path) throws FileNotFoundException {
		return new FileInputStream(path);
	}

	public static List<String> getFileContent(String filename, Class<? extends Object> clazz)
			throws FileNotFoundException {
		List<String> res = new ArrayList<String>();

		// get the InputStream of the file
		Scanner in = null;
		try {
			in = new Scanner(getInputStream(filename, clazz));
			// now read all the line of the file and return them in a list
			while (in.hasNextLine())
				res.add(in.nextLine());
		} finally {
			if(in != null)
				in.close();
		}

		return res;
	}
}
