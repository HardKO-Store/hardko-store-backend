package pe.edu.upc.hardko.store.orders.interfaces.rest.resources;

import java.util.Date;
import java.util.List;

public record CreateOrderResource(
        Date orderDate,
        String userId,
        Double totalAmount,
        List<ProductItemResource> items
) {
}
