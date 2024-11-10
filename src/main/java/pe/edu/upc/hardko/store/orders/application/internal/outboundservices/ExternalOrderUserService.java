package pe.edu.upc.hardko.store.orders.application.internal.outboundservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.IAM.interfaces.acl.UserContextFacade;
import pe.edu.upc.hardko.store.orders.domain.model.valueobjects.DeliveryAddress;

@Service
public class ExternalOrderUserService {

    private final UserContextFacade userContextFacade;

    public ExternalOrderUserService(UserContextFacade userContextFacade) {
        this.userContextFacade = userContextFacade;
    }

    public boolean existsUserById(String userId) {
        return this.userContextFacade.existsUserById(userId);
    }

    public DeliveryAddress getUserAddressById(String userId) {

        var userAddress = this.userContextFacade.GetUserAddressById(userId).get();

        var deliveryAddress = new DeliveryAddress(
                userAddress.country(),
                userAddress.city(),
                userAddress.street(),
                userAddress.zip()
        );

        return deliveryAddress;
    }


}
