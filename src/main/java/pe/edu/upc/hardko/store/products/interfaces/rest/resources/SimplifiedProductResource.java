package pe.edu.upc.hardko.store.products.interfaces.rest.resources;



public record SimplifiedProductResource(
        String id,
        String name,
        String description,
        Double price
) {
}
