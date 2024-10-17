public class Medicine {

	private int price;
	private String name;

	public int getPrice() {
		return this.price;
	}

	public Medicine(String name, int price) {
        this.name = name;
        this.price = price;
    }

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String printMedicine() {
        return "Medicine: " + name + ", Price: $" + price;
    }

	public void getAttribute() {
		// TODO - implement Medicine.getAttribute
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param attribute
	 */
	public void setAttribute(int attribute) {
		// TODO - implement Medicine.setAttribute
		throw new UnsupportedOperationException();
	}

}