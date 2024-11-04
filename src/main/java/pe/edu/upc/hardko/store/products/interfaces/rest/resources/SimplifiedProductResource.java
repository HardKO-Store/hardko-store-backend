package pe.edu.upc.hardko.store.products.interfaces.rest.resources;

public record SimplifiedProductResource(
        Long id,
        String name,
        String description,
        Double price
) {
}
