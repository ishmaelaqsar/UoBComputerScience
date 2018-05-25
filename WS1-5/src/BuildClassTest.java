import java.util.ArrayList;

/**
 * 
 */

/**
 * @author ixa444
 *
 */
public class BuildClassTest {

	ArrayList<Var> list = new ArrayList<Var>();

	// Test 1
	@org.junit.Test
	public void test1() {
		Var one = new Var("String", "Name");
		Var two = new Var("int", "Age");
		list.add(one);
		list.add(two);
		BuildClass a = new BuildClass("One", list);
		String actual1 = a.makeFields();
		String expected1 = "private String Name;" + "\n" + "private int Age;";

		assertArrayEquals(actual1, expected1);
	}

	private void assertArrayEquals(String actual1, String expected1) {
		// TODO Auto-generated method stub

	}

	// Test 2
	@org.junit.Test
	public void test2() {
		Var one = new Var("String", "Name");
		Var two = new Var("int", "Age");
		list.add(one);
		list.add(two);
		BuildClass a = new BuildClass("One", list);
		String actual2 = a.makeConstructor();
		String expected2 = "public One(String Name, int Age) {" + "\n" + "\tthis.Name = Name;" + "\n"
				+ "\tthis.Age = Age;" + "\n}";

		assertArrayEquals(actual2, expected2);
	}

	// Test 3
	@org.junit.Test
	public void test3() {
		Var one = new Var("String", "Name");
		Var two = new Var("int", "Age");
		list.add(one);
		list.add(two);
		BuildClass a = new BuildClass("One", list);
		String actual3 = a.makeFields();
		String expected3 = "private String Name;" + "\n" + "private int Age;" + "\n" + "private char Sex" + "\n"
				+ "private double Savings" + "\n" + "private double Weight";

		assertArrayEquals(actual3, expected3);
	}

	// Test 4
	@org.junit.Test
	public void test4() {
		Var one = new Var("String", "Name");
		Var two = new Var("int", "Age");
		list.add(one);
		list.add(two);
		BuildClass a = new BuildClass("One", list);
		String actual4 = a.makeGetters();
		String expected4 = "public String getName() {" + "\n" + "\treturn Name;" + "\n}\n\n" + "public int getAge() {"
				+ "\n" + "\treturn Age;" + "\n}";

		assertArrayEquals(actual4, expected4);
	}

	// Test 5
	@org.junit.Test
	public void test5() {
		Var one = new Var("String", "Name");
		Var two = new Var("int", "Age");
		list.add(one);
		list.add(two);
		BuildClass a = new BuildClass("One", list);
		String actual5 = a.makeSetters();
		String expected5 = "public void String setName(String name) {" + "\n" + "\tthis.name = name;" + "\n}\n\n" + "public void int setAge(int age) {"
				+ "\n" + "\tthis.age = age;" + "\n}";

		assertArrayEquals(actual5, expected5);
	}
}
