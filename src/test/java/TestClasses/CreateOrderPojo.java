package TestClasses;

import java.util.List;

public class CreateOrderPojo {
    public List<OrderChildPojo> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderChildPojo> orders) {
        this.orders = orders;
    }

    private List<OrderChildPojo> orders;
}
