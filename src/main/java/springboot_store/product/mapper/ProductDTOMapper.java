package springboot_store.product.mapper;

import org.springframework.stereotype.Component;
import springboot_store.product.dto.ProductDTO;
import springboot_store.product.model.Product;

import java.util.function.Function;

@Component
public class ProductDTOMapper implements Function<Product, ProductDTO> {
    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getProductId(),
                product.getName(),
                product.getUnitPrice()
        );
    }
}