package pe.edu.upc.hardko.store.IAM.domain.services;

import pe.edu.upc.hardko.store.IAM.domain.model.aggregates.User;
import pe.edu.upc.hardko.store.IAM.domain.model.queries.GetAllUsersQuery;
import pe.edu.upc.hardko.store.IAM.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.hardko.store.IAM.domain.model.queries.GetUserFavoriteProductsQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    Optional<User> handle(GetUserByIdQuery query);
    List<User> handle(GetAllUsersQuery query);
    List<String> handle(GetUserFavoriteProductsQuery query);
}
