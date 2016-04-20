package vn.edu.vnu.uet.nlp.segmenter.bin;

import java.io.IOException;
import java.util.List;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import vn.edu.vnu.uet.nlp.segmenter.SegmentationSystem;
import vn.edu.vnu.uet.nlp.utils.FileUtils;

/**
 * @author tuanphong94
 *
 */
public class Test {

	/**
	 * @param modelsPath
	 * @param testSet
	 */
	private static void test(String modelsPath, String testSet) {
		System.out.println("Models path:\t" + modelsPath);
		SegmentationSystem machine = null;

		try {
			System.out.println("Load model...\n");
			machine = new SegmentationSystem(modelsPath);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return;
		}

		System.out.println("Test file:\t" + testSet + "\n");
		List<String> dataLines = null;

		try {
			dataLines = FileUtils.readFile(testSet);
			machine.test(dataLines);
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestOption option = new TestOption();
		CmdLineParser parser = new CmdLineParser(option);

		if (args.length != 4) {
			System.out.println(Test.class.getName() + " [arguments..]");
			parser.printUsage(System.out);
			return;
		}

		try {
			parser.parseArgument(args);
			test(option.modelsPath.getPath(), option.inFile.getPath());
		} catch (CmdLineException e) {
			System.out.println(Test.class.getName() + " [arguments..]");
			e.printStackTrace();
		}
	}

}
