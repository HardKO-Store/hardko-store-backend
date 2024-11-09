package pe.edu.upc.hardko.store.reviews.domain.services;

import pe.edu.upc.hardko.store.reviews.domain.model.aggregates.Review;
import pe.edu.upc.hardko.store.reviews.domain.model.queries.GetReviewByIdQuery;
import pe.edu.upc.hardko.store.reviews.domain.model.queries.GetReviewsByProductIdQuery;
import pe.edu.upc.hardko.store.reviews.domain.model.queries.GetReviewsByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {
    List<Review> handle(GetReviewsByProductIdQuery query);
    List<Review> handle(GetReviewsByUserIdQuery query);
    Optional<Review> handle(GetReviewByIdQuery query);
}
