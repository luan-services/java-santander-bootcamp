public enum OrderStatus {
    PENDING("The order is waiting for payment"),
    PAID("The payment was confirmed"),
    SHIPPED("The order was sent"),
    DELIVERED("The order reached the customer"),
    CANCELED("The order was canceled");

    private final String description;

    /* enum constructors are private because constants are the only allowed instances */
    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
