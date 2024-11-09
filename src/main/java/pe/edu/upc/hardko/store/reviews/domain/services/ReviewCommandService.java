package pe.edu.upc.hardko.store.reviews.domain.services;

import pe.edu.upc.hardko.store.reviews.domain.model.aggregates.Review;
import pe.edu.upc.hardko.store.reviews.domain.model.commands.AddLikeToReviewByIdCommand;
import pe.edu.upc.hardko.store.reviews.domain.model.commands.CreateReviewCommand;
import pe.edu.upc.hardko.store.reviews.domain.model.commands.DeleteReviewCommand;

import java.util.Optional;

public interface ReviewCommandService {
    Optional<Review> handle(CreateReviewCommand command);
    void handle(DeleteReviewCommand command);
    void handle(AddLikeToReviewByIdCommand command);
}
