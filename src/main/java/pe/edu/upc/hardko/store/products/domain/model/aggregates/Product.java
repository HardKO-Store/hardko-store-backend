package pe.edu.upc.hardko.store.products.domain.model.aggregates;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.edu.upc.hardko.store.products.domain.model.commands.CreateProductCommand;
import pe.edu.upc.hardko.store.products.domain.model.entities.ProductOptions;
import pe.edu.upc.hardko.store.shared.domain.model.entities.AuditableModel;

import java.util.List;

@Getter
@Document(collection = "products")
@AllArgsConstructor
public class Product extends AuditableModel {

    @NonNull
    private String name;

    @NonNull
    private String description;


    //TODO: refactor categories into value object
    @NonNull
    private List<String> categories;

    @NonNull
    private Double price;

    @NonNull
    private String brand;

    @NonNull
    private Integer stock;

    @NonNull
    private String imageurl;

    @NonNull
    private ProductOptions options;

    //TODO: implement review class and entity with connection to users

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
