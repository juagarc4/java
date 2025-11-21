package sales;

public class Order {

    private final int idOrder;
    private Product[] products;
    private int counterProducts;
    private static final int MAX_PRODUCTS = 10;
    private static int counterOrders;

    public Order() {
        this.idOrder = ++Order.counterOrders;
        this.products = new Product[MAX_PRODUCTS];
    }

    public void addProduct(Product product) {
        if (this.counterProducts < Order.MAX_PRODUCTS) {
            this.products[this.counterProducts++] = product;
        } else {
            System.out.printf("You can't add more products to the order. The limit is %d", MAX_PRODUCTS);
        }
    }
    public double totalOrder() {
        double total = 0;
        for(int i = 0; i < this.counterProducts; i++ ) {
            Product product = this.products[i];
            total += product.getPrice();
        }
        return total;
    }

    public void showOrder() {
        System.out.printf("Order ID: %d", this.idOrder);
        System.out.printf("\n\tTotal: %s", this.totalOrder());
        System.out.println("\n\tOrder products:");
        for(int i=0; i < this.counterProducts; i++ ) {
            System.out.printf("\t\t%s\n", this.products[i]);
        }
    }
}
