package christmas.model;

public class Customer {
    private Order order;
    private Badge badge;

    public Customer(Order order) {
        this.order = order;
        this.badge = new Badge();
    }

    public Order getOrder() {
        return order;
    }

    public Badge getBadge() {
        return badge;
    }
}
