import java.util.Optional;

class Product {
    private Price price;

    Product(Price price) {
        this.price = price;
    }

    public Price getPrice() {
        return price;
    }
}

class Price {
    private Double amount;

    Price(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }
}

class CartItem {
    private Product product;
    private Integer quantity;

    CartItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }
}

public class Optional7CodeExample {

    public static void main(String[] args) {

        CartItem item1 = new CartItem(new Product(new Price(50.0)), 2);
        CartItem item2 = new CartItem(new Product(new Price(null)), 3);
        CartItem item3 = new CartItem(new Product(null), 3);
        CartItem item4 = new CartItem(null, 3);
        CartItem item5 = new CartItem(new Product(new Price(20.0)), null);
        CartItem item6 = new CartItem(new Product(new Price(-10.0)), 3);
        CartItem item7 = new CartItem(new Product(new Price(15.0)), -2);

        System.out.println(getTotalPrice(item1)); // valid
        System.out.println(getTotalPrice(item2)); // price null
        System.out.println(getTotalPrice(item3)); // product -> price null
        System.out.println(getTotalPrice(item4)); // product null
        System.out.println(getTotalPrice(item5)); // quantity null
        System.out.println(getTotalPrice(item6)); // amount negative
        System.out.println(getTotalPrice(item7)); // quantity negative
    }

    // ---------------------------------------------------------
    // Computes total price using Optional chaining
    // Rules:
    // - If product/price/amount/quantity missing => return 0.0
    // - If amount <= 0 or quantity <= 0 => return 0.0
    // ---------------------------------------------------------
    public static double getTotalPrice(CartItem item) {
        return Optional.ofNullable(item)
        .map(CartItem::getProduct)
        .map(Product::getPrice)
        .map(Price::getAmount)
        .filter(amount -> amount>0)
        .flatMap(
            amount -> Optional.ofNullable(item.getQuantity())
                        .filter(quantity -> quantity>0)
                        .map(quantity -> amount*quantity)
        )
        .orElse(0.0);
    }
}
