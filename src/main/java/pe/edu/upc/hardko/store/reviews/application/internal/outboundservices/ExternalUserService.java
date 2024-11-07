package pe.edu.upc.hardko.store.reviews.application.internal.outboundservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.IAM.interfaces.acl.UserContextFacade;
import pe.edu.upc.hardko.store.products.interfaces.acl.ProductsContextFacade;

@Service
public class ExternalUserService {

    private final UserContextFacade userContextFacade;

    public ExternalUserService(UserContextFacade userContextFacade) {
        this.userContextFacade = userContextFacade;
    }

    public boolean existsByUserId(String userId){
        return this.userContextFacade.existsUserById(userId);
    }

}
