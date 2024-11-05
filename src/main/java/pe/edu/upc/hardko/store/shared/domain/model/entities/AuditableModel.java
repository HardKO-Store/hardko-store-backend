package pe.edu.upc.hardko.store.shared.domain.model.entities;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;


public class AuditableModel {

    @Getter
    @Id
    private String id;

    @Getter
    @CreatedDate
    @Field("created_at")
    private Date createdAt;

    @Getter
    @LastModifiedDate
    @Field("updated_at")
    private Date updatedAt;
}
