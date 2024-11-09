package pe.edu.upc.hardko.store.orders.domain.model.aggregates;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import pe.edu.upc.hardko.store.orders.domain.model.entities.OrderShipping;
import pe.edu.upc.hardko.store.orders.domain.model.valueobjects.OrderStatuses;
import pe.edu.upc.hardko.store.orders.domain.model.valueobjects.ProductItem;
import pe.edu.upc.hardko.store.shared.domain.model.entities.AuditableModel;

import java.util.Date;
import java.util.List;

@Getter
@Document(collection = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order extends AuditableModel {

    @NotNull
    @Field("order_date")
    private Date orderDate;

    @NotNull
    @Field("user_id")
    private String userId;

    @NotNull
    private OrderStatuses status;

    @NotNull
    @Field("total_amount")
    private Double totalAmount;

    @NotNull
    private List<ProductItem> items;

    @NotNull
    private OrderShipping shipping;


}
