package pe.edu.upc.hardko.store.products.interfaces.rest.resources;

public record UpdateProductResource(
        String name,
        String description,
        Double price,
        String category,
        String brand,
        Integer stock,
        String image,
        String size,
        String color
) {
}
