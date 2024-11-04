package pe.edu.upc.hardko.store.products.interfaces.rest.resources;

public record CreateProductResource(
        String name,
        String description,
        String category,
        Double price,
        String brand,
        Integer stock,
        String image,
        String size,
        String color
) {
}
