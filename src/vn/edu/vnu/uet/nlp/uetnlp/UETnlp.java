package vn.edu.vnu.uet.nlp.uetnlp;

import java.util.ArrayList;
import java.util.List;

import vn.edu.vnu.uet.nlp.postag.UETTagger;
import vn.edu.vnu.uet.nlp.segmenter.UETSegmenter;

/**
 * @author tuanphong94
 *
 */
public class UETnlp {
	private static UETSegmenter segmenter = null;
	private static UETTagger tagger = null;

	static {
		loadModels();
	}

	private static void loadModels() {
		segmenter = new UETSegmenter();
		tagger = new UETTagger();
	}

	/**
	 * @param rawText
	 *            a raw text
	 * @return POS-tagged text
	 */
	public static String tagRawText(String rawText) {
		return tagger.tagString(segmenter.segment(rawText));
	}

	/**
	 * @param segmentedText
	 *            a word-segmented text
	 * @return POS-tagged text
	 */
	public static String tagSegmentedText(String segmentedText) {
		return tagger.tagString(segmentedText);
	}

	/**
	 * @param rawText
	 *            a raw text
	 * @return list of word-segmented and POS-tagged sentences
	 */
	public static List<String> tagSentences(String rawText) {
		List<String> segmentedSents = segmentSentences(rawText);
		List<String> taggedSents = new ArrayList<String>();

		for (String sent : segmentedSents) {
			taggedSents.add(tagSegmentedText(sent));
		}

		return taggedSents;
	}

	/**
	 * @param rawText
	 *            a raw text
	 * @return word-segmented text
	 */
	public static String segmentRawText(String rawText) {
		return segmenter.segment(rawText);
	}

	/**
	 * @param tokenizedText
	 *            a tokenized text
	 * @return word-segmented text
	 */
	public static String segmentTokenizedText(String tokenizedText) {
		return segmenter.segmentTokenizedText(tokenizedText);
	}

	/**
	 * @param rawText
	 *            a raw text
	 * @return List of word-segmented sentences
	 */
	public static List<String> segmentSentences(String rawText) {
		return segmenter.segmentSentences(rawText);
	}

}
