package pe.edu.upc.hardko.store.orders.domain.services;

import pe.edu.upc.hardko.store.orders.domain.model.aggregates.Order;
import pe.edu.upc.hardko.store.orders.domain.model.commands.CancelOrderByIdCommand;
import pe.edu.upc.hardko.store.orders.domain.model.commands.CreateOrderCommand;
import pe.edu.upc.hardko.store.orders.domain.model.commands.UpdateOrderCommand;

import java.util.Optional;

public interface OrderCommandService {
    Optional<Order> handle(CreateOrderCommand command);
    Optional<Order> handle(UpdateOrderCommand command);
    void handle(CancelOrderByIdCommand command);
}
