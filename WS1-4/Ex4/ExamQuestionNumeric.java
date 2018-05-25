/**
 * 
 */

/**
 * @author ishmael
 *
 */
public class ExamQuestionNumeric extends ExamQuestion {

	private int answer;

	/**
	 * @param questionText
	 * @param maximalMark
	 * @param answer
	 */
	public ExamQuestionNumeric(String questionText, int maximalMark, int answer) {
		super(questionText, maximalMark);
		this.answer = answer;
	}

	/**
	 * @return the answer
	 */
	public int getAnswer() {
		return answer;
	}

	/**
	 * @param answer
	 *            the answer to set
	 */
	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public int mark(int value) {
		int mark;
		if (value == answer) {
			mark = getMaximalMark();
		} else {
			mark = 0;
		}
		return mark;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + String.format(" Correct answer is: %d", answer);
	}

	public static void main(String[] args) {
		ExamQuestionNumeric q1 = new ExamQuestionNumeric("2+3 = ?", 10, 5);
		System.out.println(q1.toString());
		System.out.println(q1.mark(5));
		System.out.println(q1.mark(6));
	}

}
