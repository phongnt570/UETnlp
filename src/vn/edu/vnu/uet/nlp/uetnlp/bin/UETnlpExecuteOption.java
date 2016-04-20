package vn.edu.vnu.uet.nlp.uetnlp.bin;

import java.io.File;

import org.kohsuke.args4j.Option;

public class UETnlpExecuteOption {
	@Option(name = "-res", usage = "the resources folder containing model for WS and configure file for POS tagging (require)")
	File resDir;

	@Option(name = "-sent", usage = "specify if doing sentence segmentation is set or not (default: false)")
	boolean doSenSeg = false;

	@Option(name = "-tok", usage = "specify if doing tokenization is set or not (default: false)")
	boolean doToken = false;

	@Option(name = "-ws", usage = "specify if doing word segmentation is set or not (default: false)")
	boolean doWordSeg = false;

	@Option(name = "-pos", usage = "specify if doing pos tagging or not is set or not (default: false)")
	boolean doPosTagging = false;

	@Option(name = "-in", usage = "the input file/directory (require)")
	File inFile;

	@Option(name = "-ext", usage = "specify file types to process (in the case -in is a directory) (default: .txt)")
	String ext = ".txt";
}
