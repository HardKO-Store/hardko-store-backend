package pe.edu.upc.hardko.store.reviews.interfaces.rest.transform;

import pe.edu.upc.hardko.store.reviews.domain.model.aggregates.Review;
import pe.edu.upc.hardko.store.reviews.interfaces.rest.resources.ReviewResource;

public class ReviewResourceFromEntityAssembler {
    public static ReviewResource toResourceFromEntity(Review entity){
        return new ReviewResource(
                entity.getId(),
                entity.getProductId(),
                entity.getUserId(),
                entity.getRating(),
                entity.getTitle(),
                entity.getComment(),
                entity.getLikedBy().size()
        );
    }
}
