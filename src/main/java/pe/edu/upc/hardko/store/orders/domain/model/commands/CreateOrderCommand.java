package pe.edu.upc.hardko.store.orders.domain.model.commands;

import pe.edu.upc.hardko.store.orders.domain.model.valueobjects.ProductItem;

import java.util.Date;
import java.util.List;

public record CreateOrderCommand(
        Date orderDate,
        String userId,
        Double totalAmount,
        List<ProductItem> items
) {
}
