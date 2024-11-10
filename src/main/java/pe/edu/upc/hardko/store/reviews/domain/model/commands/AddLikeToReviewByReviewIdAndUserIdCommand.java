package pe.edu.upc.hardko.store.reviews.domain.model.commands;

public record AddLikeToReviewByReviewIdAndUserIdCommand(String reviewId, String userId) {
}
