package pe.edu.upc.hardko.store.products.domain.model.entities;

import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
public class ProductOptions {

    @NonNull
    private List<String> sizes;

    @NonNull
    private List<String> colors;

    public ProductOptions(List<String> sizes, List<String> colors) {
        this.sizes = sizes;
        this.colors = colors;
    }
}
