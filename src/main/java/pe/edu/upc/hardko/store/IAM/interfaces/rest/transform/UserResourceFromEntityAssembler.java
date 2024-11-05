package pe.edu.upc.hardko.store.IAM.interfaces.rest.transform;

import pe.edu.upc.hardko.store.IAM.domain.model.aggregates.User;
import pe.edu.upc.hardko.store.IAM.interfaces.rest.resoruces.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity){
        return new UserResource(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword(),
                UserAddressResourceFromEntityAssembler.toResourceFromEntity(entity.getAddress()),
                entity.getFavoriteProducts()
        );
    }
}
