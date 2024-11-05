package pe.edu.upc.hardko.store.IAM.interfaces.rest.transform;

import pe.edu.upc.hardko.store.IAM.domain.model.entities.UserAddress;
import pe.edu.upc.hardko.store.IAM.interfaces.rest.resoruces.UserAddressResource;

public class UserAddressResourceFromEntityAssembler {
    public static UserAddressResource toResourceFromEntity(UserAddress entity){
        return new UserAddressResource(
                entity.getCountry(),
                entity.getCity(),
                entity.getStreet(),
                entity.getZip()
        );
    }
}
