package pe.edu.upc.hardko.store.reviews.domain.model.queries;

import pe.edu.upc.hardko.store.reviews.domain.model.valueobjects.UserId;

public record GetReviewsByUserIdQuery(UserId userId) {
}
