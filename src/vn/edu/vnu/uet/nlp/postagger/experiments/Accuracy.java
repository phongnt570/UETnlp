package vn.edu.vnu.uet.nlp.postagger.experiments;

public class Accuracy {
	private int totalWord;
	private int rightWord;
	private int rightUnknownWord;
	private int numUnknownWord;

	public Accuracy(int totalWord, int rightWord, int rightUnknownWord, int numUnknownWord) {
		this.totalWord = totalWord;
		this.rightWord = rightWord;
		this.rightUnknownWord = rightUnknownWord;
		this.numUnknownWord = numUnknownWord;
	}

	public int getTotalWord() {
		return totalWord;
	}

	public int getRightWord() {
		return rightWord;
	}

	public int getRightUnknownWord() {
		return rightUnknownWord;
	}

	public int getNumUnknownWord() {
		return numUnknownWord;
	}

	public double getAccuracy() {
		return 100.0 * rightWord / totalWord;
	}

	public double getUnkAccuracy() {
		return 100.0 * rightUnknownWord / numUnknownWord;
	}

	public String toString() {
		return getAccuracy() + "%\t" + getUnkAccuracy() + "%";
	}
}
