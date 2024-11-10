package pe.edu.upc.hardko.store.IAM.interfaces.rest.transform;

import pe.edu.upc.hardko.store.IAM.domain.model.valueobjects.UserAddress;
import pe.edu.upc.hardko.store.IAM.interfaces.rest.resoruces.UserAddressResource;

public class UserAddressResourceFromEntityAssembler {
    public static UserAddressResource toResourceFromEntity(UserAddress entity){
        return new UserAddressResource(
                entity.country(),
                entity.city(),
                entity.street(),
                entity.zip()
        );
    }
}
