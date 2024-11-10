package pe.edu.upc.hardko.store.orders.interfaces.rest.resources;

public record OrderShippingResource(
        DeliveryAddressResource deliveryAddress,
        String trackingNumber
) {
}
