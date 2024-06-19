package springboot_store.sale.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import springboot_store.product.dto.ProductDTO;
import springboot_store.product.mapper.ProductDTOMapper;
import springboot_store.sale.dto.SaleDTO;
import springboot_store.sale.model.Sale;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SaleDTOMapper implements Function<Sale, SaleDTO> {

    private final ProductDTOMapper productDTOMapper;

    @Override
    public SaleDTO apply(Sale sale) {

        Set<ProductDTO> productDTOS = sale.getProducts()
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toSet());

        return new SaleDTO(
                sale.getSaleId(),
                sale.getCode(),
                productDTOS
        );
    }
}