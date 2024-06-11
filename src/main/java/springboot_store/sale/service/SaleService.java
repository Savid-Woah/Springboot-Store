package springboot_store.sale.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import springboot_store.exception.BackendException;
import springboot_store.product.model.Product;
import springboot_store.product.repository.ProductRepository;
import springboot_store.sale.dto.SaleDTO;
import springboot_store.sale.mapper.SaleDTOMapper;
import springboot_store.sale.model.Sale;
import springboot_store.sale.repository.SaleRepository;
import springboot_store.sale.request.SaleRequest;
import springboot_store.store.model.Store;
import springboot_store.store.repository.StoreRepository;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static springboot_store.exception.MsgCode.PRODUCT_NOT_FOUND;
import static springboot_store.exception.MsgCode.STORE_NOT_FOUND;
import static springboot_store.response.handler.ResponseHandler.generateResponse;
import static springboot_store.response.response.ResponseMessage.SALE_ADDED;
import static springboot_store.utils.PricingUtils.calculateSaleFinalPrice;

@Service
@Transactional
@RequiredArgsConstructor
public class SaleService {

    private final SaleDTOMapper saleDTOMapper;
    private final SaleRepository saleRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    public Page<SaleDTO> getAllSalesByStore(UUID storeId, Integer pageNumber, Integer pageSize) {

        Store store = storeRepository.findById(storeId).orElseThrow(() -> new BackendException(STORE_NOT_FOUND));
        return saleRepository.findAllByStore(store, PageRequest.of(pageNumber, pageSize)).map(saleDTOMapper);
    }

    public Map<String, Object> addSale(SaleRequest saleRequest) {

        Sale sale = buildSale(saleRequest);
        saleRepository.save(sale);

        return generateResponse(CREATED, SALE_ADDED);
    }

    private Sale buildSale(SaleRequest saleRequest) {

        Set<Product> products = resolveSaleProducts(saleRequest);
        Double finalPrice = calculateSaleFinalPrice(products);
        return new Sale(products, finalPrice);
    }

    public Set<Product> resolveSaleProducts(SaleRequest saleRequest) {

        Set<UUID> productIds = saleRequest.getProductIds();
        Set<Product> products = new HashSet<>();

        for(UUID productId: productIds) {

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new BackendException(PRODUCT_NOT_FOUND));

            products.add(product);
        }

        return products;
    }
}