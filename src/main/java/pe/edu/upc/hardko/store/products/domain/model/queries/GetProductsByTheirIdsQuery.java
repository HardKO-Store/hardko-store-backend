package pe.edu.upc.hardko.store.products.domain.model.queries;

import java.util.List;

public record GetProductsByTheirIdsQuery(List<String> productsIds) {
}
