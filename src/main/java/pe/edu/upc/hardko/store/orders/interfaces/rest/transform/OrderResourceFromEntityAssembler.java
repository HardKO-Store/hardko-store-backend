package pe.edu.upc.hardko.store.orders.interfaces.rest.transform;

import pe.edu.upc.hardko.store.orders.domain.model.aggregates.Order;
import pe.edu.upc.hardko.store.orders.interfaces.rest.resources.OrderResource;

import java.util.stream.Collectors;

public class OrderResourceFromEntityAssembler {
    public static OrderResource toResourceFromEntity(Order order){
        return new OrderResource(
                order.getId(),
                order.getOrderDate(),
                order.getUserId(),
                order.getStatus().toString(),
                order.getTotalAmount(),
                order.getItems().stream()
                        .map(ProductItemResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList()),
                OrderShippingResourceFromEntityAssembler.toResourceFromEntity(order.getShipping())
        );
    }
}
