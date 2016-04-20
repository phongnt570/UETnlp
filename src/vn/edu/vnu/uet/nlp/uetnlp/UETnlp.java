package vn.edu.vnu.uet.nlp.uetnlp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vn.edu.vnu.uet.nlp.postagger.UETTagger;
import vn.edu.vnu.uet.nlp.segmenter.UETSegmenter;
import vn.edu.vnu.uet.nlp.tokenizer.Tokenizer;
import vn.edu.vnu.uet.nlp.uetnlp.bin.Execute;

/**
 * @author tuanphong94
 *
 */
public class UETnlp {
	private UETSegmenter segmenter = null;
	private UETTagger tagger = null;
	private File resDir;
	private boolean doSenSeg = false;
	private boolean doToken = false;
	private boolean doWordSeg = false;
	private boolean doPosTagging = false;

	public UETnlp() {
		this(new File("resources"), true, true, true, true);
	}

	public UETnlp(File resDir, boolean doSenSeg, boolean doToken, boolean doWordSeg, boolean doPosTagging) {
		this.resDir = resDir;
		this.doSenSeg = doSenSeg;
		this.doToken = doToken;
		this.doWordSeg = doWordSeg;
		this.doPosTagging = doPosTagging;
		init();
	}

	public UETnlp(File resDir) {
		this.resDir = resDir;
		init();
	}

	public void init() {
		if (doWordSeg) {
			segmenter = new UETSegmenter(resDir.getPath() + File.separator + "segmenter" + File.separator + "models");
		}

		if (doPosTagging) {
			tagger = new UETTagger(resDir.getPath() + File.separator + "postagger" + File.separator + "configure"
					+ File.separator + "config_decode_pos_vi.xml");
		}
	}

	/**
	 * Called by {@link Execute}
	 * 
	 * @param text
	 * @return processed text
	 */
	public String process(String text) {
		StringBuilder sb = new StringBuilder();

		String[] lines = text.split("\\n+");

		List<String> tokenizedLines = new ArrayList<String>();

		if (doToken) {
			for (String line : lines) {
				List<String> tokens = null;
				try {
					tokens = Tokenizer.tokenize(line);
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(0);
				}

				if (doSenSeg) {
					tokenizedLines.addAll(Tokenizer.joinSentences(tokens));
				} else {
					StringBuilder lineBuilder = new StringBuilder();
					for (String tok : tokens) {
						lineBuilder.append(tok + " ");
					}
					tokenizedLines.add(lineBuilder.toString().trim());
				}
			}
		} else {
			for (String line : lines) {
				if (!doSenSeg) {
					tokenizedLines.add(line);
				} else {
					List<String> tokens = new ArrayList<String>();
					tokens.addAll(Arrays.asList(line.split("\\s+")));

					tokenizedLines.addAll(Tokenizer.joinSentences(tokens));
				}
			}
		}

		List<String> segmentedLines = new ArrayList<String>();

		if (!doWordSeg) {
			segmentedLines = tokenizedLines;
		} else {
			for (String line : tokenizedLines) {
				segmentedLines.add(segmenter.segmentTokenizedText(line));
			}
		}

		List<String> posTaggedLines = new ArrayList<String>();

		if (!doPosTagging) {
			posTaggedLines = segmentedLines;
		} else {
			for (String line : segmentedLines) {
				posTaggedLines.add(tagger.tagString(line));
			}
		}

		for (String line : posTaggedLines) {
			sb.append(line + "\n");
		}

		return sb.toString().trim();
	}

	// ----------APIs for WS-----------

	/**
	 * @param rawText
	 * @return word-segmented text
	 */
	public String segmentRawText(String rawText) {
		return segmenter.segment(rawText);
	}

	/**
	 * @param tokenizedText
	 *            a tokenized text
	 * @return word-segmented text
	 */
	public String segmentTokenizedText(String tokenizedText) {
		return segmenter.segmentTokenizedText(tokenizedText);
	}

	/**
	 * @param rawText
	 * @return list of word-segmented sentences
	 */
	public List<String> segmentSentences(String rawText) {
		return segmenter.segmentSentences(rawText);
	}

	// ----------APIs for POS tagging-----------

	/**
	 * @param rawText
	 * @return pos-tagged text
	 */
	public String tagRawText(String rawText) {
		return tagger.tagString(segmentRawText(rawText));
	}

	/**
	 * @param segmentedText
	 *            a word-segmented text
	 * @return pos-tagged text
	 */
	public String tagSegmentedText(String segmentedText) {
		return tagger.tagString(segmentedText);
	}

	/**
	 * @param rawText
	 * @return list of pos-tagged sentences
	 */
	public List<String> tagSentences(String rawText) {
		List<String> results = new ArrayList<String>();

		List<String> sents = segmentSentences(rawText);

		for (String sent : sents) {
			results.add(tagger.tagString(sent));
		}

		return results;
	}

	/**
	 * Change threshold r for post-processing
	 * 
	 * @param r
	 *            threshold r
	 */
	public void setR(double r) {
		this.segmenter.setR(r);
	}
}
