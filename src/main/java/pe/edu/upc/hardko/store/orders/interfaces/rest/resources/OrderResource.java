package pe.edu.upc.hardko.store.orders.interfaces.rest.resources;

import java.util.Date;
import java.util.List;

public record OrderResource(
        String id,
        Date orderDate,
        String userId,
        String status,
        Double totalAmount,
        List<ProductItemResource> items,
        OrderShippingResource shipping
) {
}
