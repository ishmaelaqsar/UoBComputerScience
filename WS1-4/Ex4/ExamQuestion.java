/**
 * 
 */

/**
 * @author ishmael
 *
 */
public class ExamQuestion {

	private String questionText;
	private int maximalMark;

	/**
	 * @param questionText
	 * @param maximalMark
	 */
	public ExamQuestion(String questionText, int maximalMark) {
		super();
		this.questionText = questionText;
		this.maximalMark = maximalMark;
	}

	/**
	 * @return the questionText
	 */
	public String getQuestionText() {
		return questionText;
	}

	/**
	 * @param questionText
	 *            the questionText to set
	 */
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	/**
	 * @return the maximalMark
	 */
	public int getMaximalMark() {
		return maximalMark;
	}

	/**
	 * @param maximalMark
	 *            the maximalMark to set
	 */
	public void setMaximalMark(int maximalMark) {
		this.maximalMark = maximalMark;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Question (Maximal mark: %d): %s", maximalMark, questionText);
	}

}
