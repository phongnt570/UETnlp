package vn.edu.vnu.uet.nlp.segmenter.bin;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.List;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import vn.edu.vnu.uet.nlp.segmenter.Configure;
import vn.edu.vnu.uet.nlp.segmenter.FeatureExtractor;
import vn.edu.vnu.uet.nlp.segmenter.SegmentationSystem;
import vn.edu.vnu.uet.nlp.utils.FileUtils;
import vn.edu.vnu.uet.nlp.utils.Logging;

/**
 * @author tuanphong94
 *
 */
public class Train {

	/**
	 * @param trainingData
	 * @param modelsPath
	 * @throws IOException
	 */
	private static void train(String trainingData, String modelsPath) {
		Path path = Paths.get(trainingData);
		BufferedReader br = null;

		try {
			br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		FeatureExtractor fe = new FeatureExtractor();

		Logging.LOG.info("extracting features...");
		String line = null;
		int cnt = 0;

		try {
			while ((line = br.readLine()) != null) {
				if (line.isEmpty())
					continue;
				fe.extract(Normalizer.normalize(line, Form.NFC), Configure.TRAIN);
				if (cnt % 1000 == 0 && cnt > 0) {
					System.out.println(cnt + " sentences extracted to features");
				}
				cnt++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(cnt + " sentences extracted to features");
		System.out.println("\t\t\t\t\t\tTotal number of unique features: " + fe.getFeatureMapSize());

		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		SegmentationSystem machine = new SegmentationSystem(fe, modelsPath);

		machine.train();
	}

	/**
	 * @param trainingFolder
	 * @param ext
	 * @param modelsPath
	 */
	private static void trainFolder(String trainingFolder, String ext, String modelsPath) {
		FeatureExtractor fe = new FeatureExtractor();
		Logging.LOG.info("extracting features...");

		File folder = new File(trainingFolder);
		if (!folder.isDirectory()) {
			Logging.LOG.error(folder + " is not a valid directory.");
			return;
		}

		File[] files = folder.listFiles();
		int cnt = 0;
		for (File file : files) {
			String filePath = file.getPath();
			if (file.isFile()) {
				if (ext.equals("*") || filePath.endsWith(ext)) {
					System.out.println("File:\t" + filePath + "\n");
					List<String> dataLines = null;
					try {
						dataLines = FileUtils.readFile(filePath.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}

					for (String line : dataLines) {
						if (line.isEmpty()) {
							continue;
						}

						fe.extract(Normalizer.normalize(line, Form.NFC), Configure.TRAIN);
						if (cnt % 1000 == 0 && cnt > 0) {
							System.out.println(cnt + " sentences extracted to features");
						}
					}
					cnt += dataLines.size();
				}
			}
		}

		System.out.println(cnt + " sentences extracted to features");
		System.out.println("\t\t\t\t\t\tTotal number of unique features: " + fe.getFeatureMapSize());

		SegmentationSystem machine = new SegmentationSystem(fe, modelsPath);

		machine.train();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TrainOption option = new TrainOption();
		CmdLineParser parser = new CmdLineParser(option);

		if (args.length < 4) {
			System.out.println(Train.class.getName() + " [options...] [arguments..]");
			parser.printUsage(System.out);
			return;
		}

		try {
			parser.parseArgument(args);

			if (option.inFile.isFile()) {
				train(option.inFile.getPath(), option.modelsPath.getPath());
			} else if (option.inFile.isDirectory()) {
				trainFolder(option.inFile.getPath(), option.ext, option.modelsPath.getPath());
			}
		} catch (CmdLineException e) {
			System.out.println(Train.class.getName() + " [options...] [arguments..]");
			e.printStackTrace();
		}
	}

}
