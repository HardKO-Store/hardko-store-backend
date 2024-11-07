package pe.edu.upc.hardko.store.reviews.domain.model.aggregates;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.edu.upc.hardko.store.reviews.domain.model.valueobjects.ProductId;
import pe.edu.upc.hardko.store.reviews.domain.model.valueobjects.UserId;
import pe.edu.upc.hardko.store.shared.domain.model.entities.AuditableModel;

@Getter
@Document(collection = "reviews")
@NoArgsConstructor
@AllArgsConstructor
public class Review extends AuditableModel {

    @NotNull

    private UserId userId;

    @NotNull
    private ProductId productId;

    @NotNull
    private Integer rating;

    @NotNull
    private String title;

    @NotNull
    private String comment;

}
