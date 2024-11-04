package pe.edu.upc.hardko.store.products.domain.model.aggregates;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.edu.upc.hardko.store.products.domain.model.entities.ProductOptions;
import pe.edu.upc.hardko.store.shared.domain.model.entities.AuditableModel;

import java.util.List;

@Getter
@Document(collection = "products")
@AllArgsConstructor
public class Product extends AuditableModel {

    @Id
    @NonNull
    private ObjectId id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private List<String> categories;

    @NonNull
    private Double price;

    @NonNull
    private String brand;

    @NonNull
    private Integer stock;

    @NonNull
    private List<String> images;

    @NonNull
    private ProductOptions options;


    public Product() {

    }




}
