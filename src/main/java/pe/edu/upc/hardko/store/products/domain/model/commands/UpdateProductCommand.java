package pe.edu.upc.hardko.store.products.domain.model.commands;

public record UpdateProductCommand(
        String productId,
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
