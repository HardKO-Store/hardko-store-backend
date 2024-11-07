package pe.edu.upc.hardko.store.reviews.domain.model.queries;

import pe.edu.upc.hardko.store.reviews.domain.model.valueobjects.ProductId;

public record GetReviewsByProductIdQuery(ProductId productId) {
}
