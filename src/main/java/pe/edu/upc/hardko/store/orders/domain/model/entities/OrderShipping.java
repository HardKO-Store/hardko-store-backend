package pe.edu.upc.hardko.store.orders.domain.model.entities;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.edu.upc.hardko.store.orders.domain.model.valueobjects.DeliveryAddress;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderShipping {

    @NotNull
    private DeliveryAddress deliveryAddress;

    @NotNull
    private String trackingNumber;

}
