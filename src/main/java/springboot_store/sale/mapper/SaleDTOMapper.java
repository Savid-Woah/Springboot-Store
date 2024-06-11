package springboot_store.sale.mapper;

import org.springframework.stereotype.Component;
import springboot_store.sale.dto.SaleDTO;
import springboot_store.sale.model.Sale;

import java.util.function.Function;

@Component
public class SaleDTOMapper implements Function<Sale, SaleDTO> {
    @Override
    public SaleDTO apply(Sale sale) {
        return new SaleDTO(
                sale.getSaleId(),
                sale.getCode()
        );
    }
}