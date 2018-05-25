import java.util.ArrayList;

/**
 * 
 */

/**
 * @author ishmael
 *
 */
public class ExamQuestionMultipleChoice extends ExamQuestion {

	private ArrayList<String> possibleAnswers = new ArrayList<String>();
	private ArrayList<Integer> correctAnswers = new ArrayList<Integer>();

	/**
	 * @param questionText
	 * @param maximalMark
	 * @param possibleAnswers
	 * @param correctAnswers
	 */
	public ExamQuestionMultipleChoice(String questionText, int maximalMark, ArrayList<String> possibleAnswers,
			ArrayList<Integer> correctAnswers) {
		super(questionText, maximalMark);
		this.possibleAnswers = possibleAnswers;
		this.correctAnswers = correctAnswers;
	}

	public int mark(ArrayList<Integer> answersProvided) {

		int mark = 0;
		double cmark = (double) getMaximalMark() / correctAnswers.size(); // marks each answer is worth
		ArrayList<Integer> duplicates = new ArrayList<Integer>(); // ArrayList to store duplicate answers

		int correct = 0; // counter for correct answers
		int incorrect = 0; // counter for incorrect answers

		for (int i : answersProvided) {
			if (correctAnswers.contains(i) && !(duplicates.contains(i))) { // check if not duplicate answer
				correct++;
				duplicates.add(i); // store in duplicates
			} else if (!correctAnswers.contains(i)) {
				incorrect++;
			}
		}

		mark = (int) Math.ceil((cmark * correct) - (cmark * incorrect));

		if (mark < 0) { // if marks are negative set to 0
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
		return super.toString()
				+ String.format("%nPossible Answers: %s %nCorrect Answers: %s %n", possibleAnswers, correctAnswers);
	}

	public static void main(String[] args) {
		ArrayList<String> possibleAnswers = new ArrayList<String>();
		possibleAnswers.add("-2");
		possibleAnswers.add("0");
		possibleAnswers.add("2");
		possibleAnswers.add("3");

		ArrayList<Integer> correctAnswers = new ArrayList<Integer>();
		correctAnswers.add(1);
		correctAnswers.add(3);

		ExamQuestionMultipleChoice question = new ExamQuestionMultipleChoice("x*x = 4", 10, possibleAnswers,
				correctAnswers);

		System.out.println(question.toString());
	}

}
