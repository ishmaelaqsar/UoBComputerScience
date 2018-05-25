import java.util.ArrayList;

public class CustomerWithGoods extends Customer {

	private ArrayList<Good> goods;

	public CustomerWithGoods(String customerName, String address, String telephoneNumber, ArrayList<Good> goods) {
		super(customerName, address, telephoneNumber);
		this.goods = goods;
	}

	/**
	 * Method to add goods a customer has bought to their instance of ArrayList goods
	 * @param goods
	 */
	public void buy(Good goods) {
		this.goods.add(goods);
	}

	/**
	 * 
	 * @return goods
	 */
	public ArrayList<Good> getGoods() {
		return goods;
	}

	/**
	 * 
	 * @return Total price of goods bought by a customer
	 */
	public int valueOfGoods() {

		int value = 0;

		for (Good i : goods)
			value += i.getPrice();

		return value;
	}

	@Override
	public String toString() {
		return "The total value of all goods " + getCustomerName() + " has bought is: £" + valueOfGoods();
	}
	
	public static class Good {

		private String name;
		private int price;

		public Good(String name, int price) {
			this.name = name;
			this.price = price;
		}

		public int getPrice() {
			return price;
		}

		@Override
		public String toString() {
			return String.format("[%s: Price = £%d]", name, price);
		}
	}
}
