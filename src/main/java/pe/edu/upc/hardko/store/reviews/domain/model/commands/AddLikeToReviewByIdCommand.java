package pe.edu.upc.hardko.store.reviews.domain.model.commands;

import pe.edu.upc.hardko.store.reviews.domain.model.valueobjects.ProductId;

public record AddLikeToReviewByIdCommand(ProductId productId) {
}
