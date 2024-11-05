package pe.edu.upc.hardko.store.IAM.domain.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {

    @NonNull
    private String country;
    @NonNull
    private String city;
    @NonNull
    private String street;
    @NonNull
    private String zip;



}
