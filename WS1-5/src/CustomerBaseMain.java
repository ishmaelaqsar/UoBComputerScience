import java.util.ArrayList;

public class CustomerBaseMain {

	public static void main(String[] args) {
		String customerName1 = "James";
		String address1 = "122 Doll Road";
		String telephoneNumber1 = "0992992";
		ArrayList<CustomerWithGoods.Good> goods1 = new ArrayList<CustomerWithGoods.Good>();

		String customerName2 = "Dario";
		String address2 = "123 Toll Road";
		String telephoneNumber2 = "0992992";
		ArrayList<CustomerWithGoods.Good> goods2 = new ArrayList<CustomerWithGoods.Good>();

		String customerName3 = "Sam";
		String address3 = "124 Roll Road";
		String telephoneNumber3 = "0992992";
		ArrayList<CustomerWithGoods.Good> goods3 = new ArrayList<CustomerWithGoods.Good>();

		CustomerWithGoods.Good one = new CustomerWithGoods.Good("TV", 400);
		CustomerWithGoods.Good two = new CustomerWithGoods.Good("Laptop", 850);
		CustomerWithGoods.Good three = new CustomerWithGoods.Good("Mouse", 50);
		CustomerWithGoods.Good four = new CustomerWithGoods.Good("Keyboard", 70);
		CustomerWithGoods.Good five = new CustomerWithGoods.Good("Phone", 700);

		CustomerWithGoods customer1 = new CustomerWithGoods(customerName1, address1, telephoneNumber1, goods1);

		customer1.buy(one);
		customer1.buy(five);

		CustomerWithGoods customer2 = new CustomerWithGoods(customerName2, address2, telephoneNumber2, goods2);

		customer2.buy(three);
		customer2.buy(four);

		CustomerWithGoods customer3 = new CustomerWithGoods(customerName3, address3, telephoneNumber3, goods3);

		customer3.buy(two);

		System.out.println(customer1.getCustomerName() + ": " + customer1.getGoods());
		System.out.println(customer2.getCustomerName() + ": " + customer2.getGoods());
		System.out.println(customer3.getCustomerName() + ": " + customer3.getGoods());
		System.out.println("");
		
		System.out.println(customer1.toString());
		System.out.println(customer2.toString());
		System.out.println(customer3.toString());
		System.out.println("");

		ArrayList<CustomerWithGoods> list = new ArrayList<CustomerWithGoods>();

		CustomerBase filter = new CustomerBase(list);

		filter.addCustomer(customer1);
		filter.addCustomer(customer2);
		filter.addCustomer(customer3);
		
		System.out.println("All Customers:");
		System.out.println(filter.toString());
		
		System.out.println("Loyal Customers:");
		System.out.println(filter.loyalToString());
		
		System.out.println("Valued Customers:");
		System.out.println(filter.valuedToString());
		
	}
}
