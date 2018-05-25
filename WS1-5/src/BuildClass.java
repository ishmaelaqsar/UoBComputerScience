import java.util.ArrayList;

/**
 * 
 */

/**
 * @author ixa444
 *
 */
public class BuildClass {

	private String className;
	private ArrayList<Var> fields;

	/**
	 * @param className name of class
	 * @param fields list of different field variables
	 */
	public BuildClass(String className, ArrayList<Var> fields) {
		this.className = className;
		this.fields = fields;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the fields
	 */
	public ArrayList<Var> getFields() {
		return fields;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public void setFields(ArrayList<Var> fields) {
		this.fields = fields;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Var> list = new ArrayList<Var>();
		Var one = new Var("String", "Name");
		Var two = new Var("int", "Age");
		list.add(one);
		list.add(two);
		BuildClass a = new BuildClass("One", list);
		System.out.println(a.buildClass());
		//System.out.println(a.makeFields());
		//System.out.println(a.makeConstructor());
		//System.out.println(a.makeGetters());
		//System.out.println(a.makeSetters());
	}

	/**
	 * Method to write field declaration(s) as String
	 * @return String of all the fields
	 */
	public String makeFields() {
		String fields = "";

		for (int i = 0; i < this.fields.size(); i++) {
			if (i == this.fields.size() - 1) {
				fields += "\tprivate " + getFields().get(i).getTypeOfVar() + " " + getFields().get(i).getNameOfVar() + ";\n\n";
			} else {
				fields += "\tprivate " + getFields().get(i).getTypeOfVar() + " " + getFields().get(i).getNameOfVar() + ";\n";
			}
		}
		
		return fields;
	}

	/**
	 * Method to write constructor as String
	 * @return Constructor as a String
	 */
	public String makeConstructor() {
		String constructor = "\tpublic " + getClassName() + "(";

		for (int i = 0; i < fields.size(); i++) {
			if (i == fields.size() - 1) {
				constructor += getFields().get(i).getTypeOfVar() + " " + fields.get(i).getNameOfVar() + ") {\n";
			} else {
				constructor += getFields().get(i).getTypeOfVar() + " " + fields.get(i).getNameOfVar() + ", ";
			}
		}

		for (int i = 0; i < fields.size(); i++) {
			if (i == fields.size() - 1) {
				constructor += "\t\tthis." + fields.get(i).getNameOfVar() + " = " + fields.get(i).getNameOfVar()
						+ ";\n \t}\n";
			} else {
				constructor += "\t\tthis." + fields.get(i).getNameOfVar() + " = " + fields.get(i).getNameOfVar()
						+ ";\n";
			}
		}

		return constructor;
	}

	/**
	 * Method to write getter method(s) as Strings
	 * @return getters as Strings
	 */
	public String makeGetters() {
		String getters = "";
		for (Var i : fields) {
			getters += "\tpublic " + i.getTypeOfVar() + " " + "get" + i.getNameOfVar().substring(0, 1).toUpperCase()
					+ i.getNameOfVar().substring(1) + "() {\n";
			getters += "\t\treturn " + i.getNameOfVar() + "; \n\t}\n\n";
		}

		return getters;
	}

	/**
	 * Method to write setter method(s) as Strings
	 * @return setters as Strings
	 */
	public String makeSetters() {
		String setters = "";
		for (Var i : fields) {
			setters += "\tpublic void " + i.getTypeOfVar() + " " + "set"
					+ i.getNameOfVar().substring(0, 1).toUpperCase() + i.getNameOfVar().substring(1) + "("
					+ i.getTypeOfVar() + " " + i.getNameOfVar().toLowerCase() + ") {\n";
			setters += "\t\tthis." + i.getNameOfVar().toLowerCase() + " = " + i.getNameOfVar().toLowerCase() + "; \n\t}\n\n";
		}

		return setters;
	}

	/**
	 * Method to combine makeFields, makeConstructor, makeGetters, makeSetters as a String
	 * @return Complete class containing all elements
	 */
	public String buildClass() {
		String Class = "public class " + getClassName() + " {\n\n" + makeFields() + makeConstructor() + makeGetters() + makeSetters() + "}";
		return Class;
	}

}

class Var {
	private String typeOfVar;
	private String nameOfVar;

	/**
	 * @param typeOfVar
	 * @param nameOfVar
	 */
	public Var(String typeOfVar, String nameOfVar) {
		this.typeOfVar = typeOfVar;
		this.nameOfVar = nameOfVar;
	}

	/**
	 * @return the typeOfVar
	 */
	public String getTypeOfVar() {
		return typeOfVar;
	}

	/**
	 * @param typeOfVar
	 *            the typeOfVar to set
	 */
	public void setTypeOfVar(String typeOfVar) {
		this.typeOfVar = typeOfVar;
	}

	/**
	 * @return the nameOfVar
	 */
	public String getNameOfVar() {
		return nameOfVar;
	}

	/**
	 * @param nameOfVar
	 *            the nameOfVar to set
	 */
	public void setNameOfVar(String nameOfVar) {
		this.nameOfVar = nameOfVar;
	}

}