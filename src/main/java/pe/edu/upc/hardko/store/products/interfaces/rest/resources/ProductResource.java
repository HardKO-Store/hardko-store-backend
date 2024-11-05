package pe.edu.upc.hardko.store.products.interfaces.rest.resources;

import java.util.List;

public record ProductResource(
        String id,
        String name,
        String description,
        List<String> categories,
        Double price,
        String brand,
        Integer stock,
        String imageurl,
        ProductOptionsResource options
) {
}
