/**
 * Represents a Medicine with a name and price.
 */
public class Medicine {

    private String name;  // Medicine name
    private int price;    // Price of the medicine

    public Medicine(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return "Medicine: " + name + ", Price: $" + price;
    }
}
