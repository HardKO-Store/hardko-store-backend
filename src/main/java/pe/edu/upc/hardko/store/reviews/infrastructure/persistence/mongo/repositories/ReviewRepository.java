package pe.edu.upc.hardko.store.reviews.infrastructure.persistence.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.hardko.store.reviews.domain.model.aggregates.Review;
import pe.edu.upc.hardko.store.reviews.domain.model.valueobjects.ProductId;
import pe.edu.upc.hardko.store.reviews.domain.model.valueobjects.UserId;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    boolean existsReviewById(String id);
    List<Review> findReviewsByProductId (ProductId productId);
    List<Review> findReviewsByUserId(UserId userId);
}
