package springboot_store.sale.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import springboot_store.product.dto.ProductDTO;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record SaleDTO(

        UUID saleId,
        String code,
        Set<ProductDTO> products

) implements Serializable {
}