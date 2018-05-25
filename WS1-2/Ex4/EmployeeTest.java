/**
 * program to test Employee.java
 */

public class EmployeeTest {

    public static void main(String[] args) {

        Employee john = new Employee("John", 10, 40); //create Employee object john

        System.out.println(john); //print johns information using toString method

        System.out.println("John has a monthly salary of " + john.monthlySalary(20) + " £"); //print johns monthly salary using monthlySalary method

        john.increaseSalary(1); //increase johns hourly salary by 1%
        System.out.println(john); //print details again showing new hourly salary
    }
}