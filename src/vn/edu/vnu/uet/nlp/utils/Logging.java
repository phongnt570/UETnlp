package vn.edu.vnu.uet.nlp.utils;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

/**
 * @author tuanphong94
 *
 */
public class Logging {
	static {
		BasicConfigurator.configure();
	}

	private Logging() {
	}

	public static final Logger LOG = Logger.getLogger(Logging.class);

	static public void initArgs(String[] args, Object bean) {

		CmdLineParser cmd = new CmdLineParser(bean);

		try {
			cmd.parseArgument(args);
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
			cmd.printUsage(System.err);
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}