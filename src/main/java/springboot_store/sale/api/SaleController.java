package springboot_store.sale.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springboot_store.sale.dto.SaleDTO;
import springboot_store.sale.request.SaleRequest;
import springboot_store.sale.service.SaleService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("store/api/v1/sales/")
public class SaleController {

    private final SaleService saleService;

    @GetMapping(path = "get-by-store/{store-id}/{page-number}/{page-size}")
    public Page<SaleDTO> getAllSalesByStore(
            @PathVariable("store-id") UUID storeId,
            @PathVariable("page-number") Integer pageNumber,
            @PathVariable("page-size") Integer pageSize
    ) {
        return saleService.getAllSalesByStore(storeId, pageNumber, pageSize);
    }

    @PostMapping
    public Map<String, Object> addSale(@Validated @RequestBody SaleRequest saleRequest) {
        return saleService.addSale(saleRequest);
    }
}