package pe.edu.upc.hardko.store.products.domain.model.aggregates;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.edu.upc.hardko.store.products.domain.model.commands.CreateProductCommand;
import pe.edu.upc.hardko.store.products.domain.model.entities.ProductOptions;
import pe.edu.upc.hardko.store.shared.domain.model.entities.AuditableModel;

import java.util.List;

@Getter
@Document(collection = "products")
@AllArgsConstructor
public class Product extends AuditableModel {

    @Id
    @NonNull
    private Long id;

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

    public Product(CreateProductCommand command) {

        this.name = command.name();
        this.description = command.description();
        this.categories =  List.of(command.category());
        this.price = command.price();
        this.brand = command.brand();
        this.stock = command.stock();
        this.images = List.of(command.image());
        this.options = new ProductOptions(
                command.size(),
                command.color()
        );
    }




}
