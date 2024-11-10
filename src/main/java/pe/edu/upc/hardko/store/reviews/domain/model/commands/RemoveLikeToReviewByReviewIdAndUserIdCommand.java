package pe.edu.upc.hardko.store.reviews.domain.model.commands;

public record RemoveLikeToReviewByReviewIdAndUserIdCommand(String reviewId, String userId) {
}
