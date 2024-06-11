package springboot_store.product.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springboot_store.product.dto.ProductDTO;
import springboot_store.product.request.ProductRequest;
import springboot_store.product.service.ProductService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("store/api/v1/products/")
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "get-by-store/{store-id}/{page-number}/{page-size}")
    public Page<ProductDTO> getAllProductsByStore(
            @PathVariable("store-id") UUID storeId,
            @PathVariable("page-number") Integer pageNumber,
            @PathVariable("page-size") Integer pageSize
    ) {
        return productService.getAllProductsByStore(storeId, pageNumber, pageSize);
    }

    @GetMapping(path = "get-by-sale/{sale-id}/{page-number}/{page-size}")
    public Page<ProductDTO> getAllProductsBySale(
            @PathVariable("sale-id") UUID saleId,
            @PathVariable("page-number") Integer pageNumber,
            @PathVariable("page-size") Integer pageSize
    ) {
        return productService.getAllProductsBySale(saleId, pageNumber, pageSize);
    }

    @PostMapping
    public Map<String, Object> addProduct(@Validated @RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }
}