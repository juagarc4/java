package sales;

public class Sales {
    public static void main(String [] args) {
        System.out.println("*** Sales System ***");
        Product product1 = new Product("T-Shirt", 30.00);
        Product product2 = new Product("Shoes", 50.00);

        Order order1 = new Order();
        order1.addProduct(product1);
        order1.addProduct(product2);
        order1.showOrder();

        Order order2 = new Order();
        order2.addProduct(product1);
        order2.addProduct(new Product("Cap", 40.00));
        order2.showOrder();
    }
}
