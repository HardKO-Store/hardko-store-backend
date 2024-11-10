package pe.edu.upc.hardko.store.orders.interfaces.rest.transform;

import pe.edu.upc.hardko.store.orders.domain.model.commands.CreateOrderCommand;
import pe.edu.upc.hardko.store.orders.interfaces.rest.resources.CreateOrderResource;

import java.util.stream.Collectors;

public class CreateOrderCommandFromResourceAssembler {
    public static CreateOrderCommand toCommandFromResource(CreateOrderResource resource){
        return new CreateOrderCommand(
                resource.orderDate(),
                resource.userId(),
                resource.totalAmount(),
                resource.items().stream()
                        .map(ProductItemFromResourceAssembler::toCommandFromResource)
                        .collect(Collectors.toList())
        );
    }
}
