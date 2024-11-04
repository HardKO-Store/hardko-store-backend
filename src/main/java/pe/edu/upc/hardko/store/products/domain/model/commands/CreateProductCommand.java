package pe.edu.upc.hardko.store.products.domain.model.commands;


public record CreateProductCommand(
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
