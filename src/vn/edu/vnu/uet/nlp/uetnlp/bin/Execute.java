package vn.edu.vnu.uet.nlp.uetnlp.bin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import vn.edu.vnu.uet.nlp.uetnlp.UETnlp;
import vn.edu.vnu.uet.nlp.utils.FileUtils;
import vn.edu.vnu.uet.nlp.utils.Logging;

public class Execute {

	static UETnlp pepline;

	public static void main(String[] args) {
		UETnlpExecuteOption option = new UETnlpExecuteOption();
		CmdLineParser parser = new CmdLineParser(option);

		if (args.length < 4) {
			System.out.println(Execute.class.getName() + " [options...] [arguments..]");
			parser.printUsage(System.out);
			return;
		}

		try {
			parser.parseArgument(args);

			pepline = new UETnlp(option.resDir, option.doSenSeg, option.doToken, option.doWordSeg, option.doPosTagging);

			if (option.inFile.isFile()) {
				Logging.LOG.info(option.inFile);
				String filePath = option.inFile.getPath();
				BufferedReader br = FileUtils.newUTF8BufferedReaderFromFile(filePath);
				BufferedWriter bw = FileUtils.newUTF8BufferedWriterFromNewFile(filePath + ".uet");

				int cnt = 0;
				for (String line; (line = br.readLine()) != null; cnt++) {
					line = line.trim();
					if (line.isEmpty()) {
						bw.newLine();
						continue;
					}

					String result = pepline.process(line);
					bw.write(result);
					bw.newLine();

					if (cnt % 1000 == 0) {
						bw.flush();
					}
				}

				bw.flush();
				bw.close();
			} else if (option.inFile.isDirectory()) {
				File[] files = option.inFile.listFiles();

				for (File file : files) {
					if (!file.getName().endsWith(option.ext)) {
						continue;
					}

					Logging.LOG.info(file);

					List<String> lines = FileUtils.readFile(file.getPath());
					List<String> results = new ArrayList<String>();
					for (String line : lines) {
						results.add(pepline.process(line));
					}

					BufferedWriter bw = FileUtils.newUTF8BufferedWriterFromNewFile(file.getPath() + ".uet");

					for (int i = 0; i < results.size(); i++) {
						if (i % 100 == 0) {
							bw.flush();
						}

						bw.write(results.get(i));
						bw.newLine();
					}

					bw.flush();
					bw.close();
				}
			}
		} catch (CmdLineException e) {
			System.out.println(Execute.class.getName() + " [options...] [arguments..]");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
