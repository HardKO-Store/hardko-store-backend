package pe.edu.upc.hardko.store.products.domain.model.aggregates;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.edu.upc.hardko.store.products.domain.model.commands.CreateProductCommand;
import pe.edu.upc.hardko.store.products.domain.model.entities.ProductOptions;
import pe.edu.upc.hardko.store.shared.domain.model.entities.AuditableModel;

import java.util.List;

@Getter
@Document(collection = "products")
@AllArgsConstructor
public class Product extends AuditableModel {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private List<String> categories;

    @NotNull
    private Double price;

    @NotNull
    private String brand;

    @NotNull
    private Integer stock;

    @NotNull
    private String imageurl;

    @NotNull
    private ProductOptions options;

    //TODO: add origin of product

    public Product() {

    }

    public Product(CreateProductCommand command) {

        this.name = command.name();
        this.description = command.description();
        this.categories =  List.of(command.category());
        this.price = command.price();
        this.brand = command.brand();
        this.stock = command.stock();
        this.imageurl = command.image();
        this.options = new ProductOptions(
                command.size(),
                command.color()
        );
    }

    public Product updateInformation(
            String name,
            String description,
            List<String> categories,
            Double price,
            String brand,
            Integer stock,
            String imageurl,
            ProductOptions options
    ){
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.price = price;
        this.brand = brand;
        this.stock = stock;
        this.imageurl = imageurl;
        this.options = options;
        return this;
    }





}
