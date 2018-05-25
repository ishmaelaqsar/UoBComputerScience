import java.util.ArrayList;

/**
 * 
 */

/**
 * @author ishmael
 *
 */
public class ExamQuestionSimpleChoice extends ExamQuestion {

	private ArrayList<String> possibleAnswers = new ArrayList<String>();
	private int correctAnswer;

	/**
	 * @param questionText
	 * @param maximalMark
	 * @param possibleAnswers
	 * @param correctAnswer
	 */
	public ExamQuestionSimpleChoice(String questionText, int maximalMark, ArrayList<String> possibleAnswers,
			int correctAnswer) {
		super(questionText, maximalMark);
		this.possibleAnswers = possibleAnswers;
		this.correctAnswer = correctAnswer;
	}

	/**
	 * @return the possibleAnswers
	 */
	public ArrayList<String> getPossibleAnswers() {
		return possibleAnswers;
	}

	/**
	 * @param possibleAnswers
	 *            the possibleAnswers to set
	 */
	public void setPossibleAnswers(ArrayList<String> possibleAnswers) {
		this.possibleAnswers = possibleAnswers;
	}

	/**
	 * @return the correctAnswer
	 */
	public int getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * @param correctAnswer
	 *            the correctAnswer to set
	 */
	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public int mark(int value) {
		int mark;
		if (value == correctAnswer) {
			mark = (int) getMaximalMark();
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
		return super.toString() + String.format("%nPossible answers are: %s%nCorrect answer position is: %d",
				possibleAnswers.toString(), correctAnswer);
	}

	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<String>();
		a.add("4");
		a.add("5");
		a.add("10");
		a.add("20");

		ExamQuestionSimpleChoice q2 = new ExamQuestionSimpleChoice("2+3 = ?", 10, a, 2);

		System.out.println(q2.toString());
		System.out.println(q2.mark(2));
		System.out.println(q2.mark(3));
	}

}
