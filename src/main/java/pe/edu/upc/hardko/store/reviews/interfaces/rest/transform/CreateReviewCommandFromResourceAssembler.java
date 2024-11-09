package pe.edu.upc.hardko.store.reviews.interfaces.rest.transform;

import pe.edu.upc.hardko.store.reviews.domain.model.commands.CreateReviewCommand;
import pe.edu.upc.hardko.store.reviews.interfaces.rest.resources.CreateReviewResource;

public class CreateReviewCommandFromResourceAssembler {
    public static CreateReviewCommand toCommandFromResource(CreateReviewResource resource){
        return new CreateReviewCommand(
                resource.productId(),
                resource.userId(),
                resource.rating(),
                resource.title(),
                resource.comment()
        );
    }
}
