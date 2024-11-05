package pe.edu.upc.hardko.store.products.interfaces.rest.resources;

import java.util.List;

public record ProductOptionsResource(
        List<String> sizes,
        List<String> colors
) {
}
