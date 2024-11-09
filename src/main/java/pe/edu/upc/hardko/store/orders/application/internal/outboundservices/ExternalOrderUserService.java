package pe.edu.upc.hardko.store.orders.application.internal.outboundservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.IAM.interfaces.acl.UserContextFacade;

@Service
public class ExternalOrderUserService {

    private final UserContextFacade userContextFacade;

    public ExternalOrderUserService(UserContextFacade userContextFacade) {
        this.userContextFacade = userContextFacade;
    }

    public boolean existsUserById(String userId) {
        return this.userContextFacade.existsUserById(userId);
    }

}
