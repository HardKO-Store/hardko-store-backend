package pe.edu.upc.hardko.store.orders.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.hardko.store.orders.domain.model.commands.CancelOrderByIdCommand;
import pe.edu.upc.hardko.store.orders.domain.model.queries.GetOrderByIdQuery;
import pe.edu.upc.hardko.store.orders.domain.model.queries.GetOrdersByProductIdQuery;
import pe.edu.upc.hardko.store.orders.domain.model.queries.GetOrdersByUserIdQuery;
import pe.edu.upc.hardko.store.orders.domain.services.OrderCommandService;
import pe.edu.upc.hardko.store.orders.domain.services.OrderQueryService;
import pe.edu.upc.hardko.store.orders.interfaces.rest.resources.CreateOrderResource;
import pe.edu.upc.hardko.store.orders.interfaces.rest.resources.OrderResource;
import pe.edu.upc.hardko.store.orders.interfaces.rest.transform.CreateOrderCommandFromResourceAssembler;
import pe.edu.upc.hardko.store.orders.interfaces.rest.transform.OrderResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Orders", description = "Orders Management Endpoints")
public class OrdersController {

    private final OrderQueryService orderQueryService;
    private final OrderCommandService orderCommandService;

    public OrdersController(OrderQueryService orderQueryService, OrderCommandService orderCommandService){
        this.orderQueryService = orderQueryService;
        this.orderCommandService = orderCommandService;
    }

    @PostMapping
    @Operation(summary = "Create a new order" , description = "Create a new order in the system")
    @ApiResponse(responseCode = "201", description = "Order created")
    public ResponseEntity<OrderResource> createOrder(@RequestBody CreateOrderResource resource){

        var createOrderCommand = CreateOrderCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var order = this.orderCommandService.handle(createOrderCommand);

        if (order.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var orderResource = OrderResourceFromEntityAssembler.toResourceFromEntity(order.get());

        return new ResponseEntity<>(orderResource, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}/cancel")
    @Operation(summary = "Cancell an order" , description = "Cancell an order in the system")
    @ApiResponse(responseCode = "200", description = "Order cancelled")
    public ResponseEntity<?> cancelOrder(@PathVariable String orderId){
        var cancelOrderCommand = new CancelOrderByIdCommand(orderId);

        this.orderCommandService.handle(cancelOrderCommand);

        return ResponseEntity.ok("The order with the id " + orderId + " has been cancelled");
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Get order by id", description = "Get an order by id")
    @ApiResponse(responseCode = "200", description = "Order found")
    public ResponseEntity<OrderResource> getOrderById(@PathVariable String orderId){

        var getOrderByIdQuery = new GetOrderByIdQuery(orderId);

        var order = this.orderQueryService.handle(getOrderByIdQuery);

        if (order.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var orderResource = OrderResourceFromEntityAssembler.toResourceFromEntity(order.get());

        return ResponseEntity.ok(orderResource);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get orders by user id", description = "Get orders by user id")
    @ApiResponse(responseCode = "200", description = "Orders found")
    public ResponseEntity<List<OrderResource>> getOrdersByUserId(@PathVariable String userId){

        var getOrdersByUserIdQuery = new GetOrdersByUserIdQuery(userId);

        var orders = this.orderQueryService.handle(getOrdersByUserIdQuery);

        if (orders.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var orderResources = orders.stream()
                .map(OrderResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(orderResources);
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Get orders by product id", description = "Get orders by product id")
    @ApiResponse(responseCode = "200", description = "Orders found")
    public ResponseEntity<List<OrderResource>> getOrdersByProductId(@PathVariable String productId){

        var getOrdersByProductIdQuery = new GetOrdersByProductIdQuery(productId);

        var orders = this.orderQueryService.handle(getOrdersByProductIdQuery);

        if (orders.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var orderResources = orders.stream()
                .map(OrderResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(orderResources);
    }


}
