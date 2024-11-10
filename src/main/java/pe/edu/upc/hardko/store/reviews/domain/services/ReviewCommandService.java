package pe.edu.upc.hardko.store.reviews.domain.services;

import pe.edu.upc.hardko.store.reviews.domain.model.aggregates.Review;
import pe.edu.upc.hardko.store.reviews.domain.model.commands.AddLikeToReviewByReviewIdAndUserIdCommand;
import pe.edu.upc.hardko.store.reviews.domain.model.commands.CreateReviewCommand;
import pe.edu.upc.hardko.store.reviews.domain.model.commands.DeleteReviewCommand;
import pe.edu.upc.hardko.store.reviews.domain.model.commands.RemoveLikeToReviewByReviewIdAndUserIdCommand;

import java.util.Optional;

public interface ReviewCommandService {
    Optional<Review> handle(CreateReviewCommand command);
    void handle(DeleteReviewCommand command);
    Optional<Integer> handle(AddLikeToReviewByReviewIdAndUserIdCommand command);
    Optional<Integer> handle(RemoveLikeToReviewByReviewIdAndUserIdCommand command);
}
