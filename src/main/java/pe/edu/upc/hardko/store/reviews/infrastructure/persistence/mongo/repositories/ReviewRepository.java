package pe.edu.upc.hardko.store.reviews.infrastructure.persistence.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.hardko.store.reviews.domain.model.aggregates.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    boolean existsReviewBy(String id);
    List<Review> findReviewsByProductId (String productId);
    List<Review> findReviewsByUserId(String userId);
}
