package pe.edu.upc.hardko.store.orders.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.orders.application.internal.outboundservices.ExternalOrderProductService;
import pe.edu.upc.hardko.store.orders.application.internal.outboundservices.ExternalOrderUserService;
import pe.edu.upc.hardko.store.orders.domain.model.aggregates.Order;
import pe.edu.upc.hardko.store.orders.domain.model.commands.CancelOrderByIdCommand;
import pe.edu.upc.hardko.store.orders.domain.model.commands.CreateOrderCommand;
import pe.edu.upc.hardko.store.orders.domain.model.commands.UpdateOrderCommand;
import pe.edu.upc.hardko.store.orders.domain.model.valueobjects.OrderStatuses;
import pe.edu.upc.hardko.store.orders.domain.services.OrderCommandService;
import pe.edu.upc.hardko.store.orders.infrastructure.persistence.mongo.repositories.OrderRepository;

import java.util.Optional;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {

    private final OrderRepository orderRepository;
    private final ExternalOrderUserService externalUserService;
    private final ExternalOrderProductService externalProductService;

    public OrderCommandServiceImpl(OrderRepository orderRepository,
                                 ExternalOrderUserService externalUserService,
                                 ExternalOrderProductService externalProductService) {
        this.orderRepository = orderRepository;
        this.externalUserService = externalUserService;
        this.externalProductService = externalProductService;
    }

    @Override
    public Optional<Order> handle(CreateOrderCommand command) {

        if (!this.externalUserService.existsUserById(command.userId())){
            throw new IllegalArgumentException("User not found");
        }

        var deliveryAddress = this.externalUserService.getUserAddressById(command.userId());

        var order = new Order(command, deliveryAddress);

        try {
            this.orderRepository.save(order);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving order: " + e.getMessage());
        }
        return Optional.of(order);

    }

    @Override
    public Optional<Order> handle(UpdateOrderCommand command) {

        return Optional.empty();
    }

    @Override
    public void handle(CancelOrderByIdCommand command) {
        if(!this.orderRepository.existsById(command.orderId())){
            throw new IllegalArgumentException("Order with id " + command.orderId() + " does not exist");
        }

        var order = this.orderRepository.findById(command.orderId()).get();

        if(order.getStatus().equals(OrderStatuses.CANCELLED)){
            throw new IllegalArgumentException("Order with id " + command.orderId() + " is already cancelled");
        }

        order.cancelOrder();

        try {
            this.orderRepository.save(order);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while cancelling order: " + e.getMessage());
        }

    }
}
