package springboot_store.product.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import springboot_store.exception.BackendException;
import springboot_store.product.dto.ProductDTO;
import springboot_store.product.mapper.ProductDTOMapper;
import springboot_store.product.model.Product;
import springboot_store.product.repository.ProductRepository;
import springboot_store.product.request.ProductRequest;
import springboot_store.sale.model.Sale;
import springboot_store.sale.repository.SaleRepository;
import springboot_store.store.model.Store;
import springboot_store.store.repository.StoreRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static springboot_store.exception.MsgCode.OOPS_ERROR;
import static springboot_store.exception.MsgCode.STORE_NOT_FOUND;
import static springboot_store.response.handler.ResponseHandler.generateResponse;
import static springboot_store.response.response.ResponseMessage.PRODUCT_ADDED;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final SaleRepository saleRepository;
    private final StoreRepository storeRepository;
    private final ProductDTOMapper productDTOMapper;
    private final ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

    public Page<ProductDTO> getAllProductsByStore(UUID storeId, Integer pageNumber, Integer pageSize) {

        Store store = storeRepository.findById(storeId).orElseThrow(() -> new BackendException(OOPS_ERROR));
        return productRepository.findAllByStore(store, PageRequest.of(pageNumber, pageSize)).map(productDTOMapper);
    }

    public Page<ProductDTO> getAllProductsBySale(UUID saleId, Integer pageNumber, Integer pageSize) {

        Sale sale = saleRepository.findById(saleId).orElseThrow(() -> new BackendException(OOPS_ERROR));

        return productRepository.findAllBySales(sale, PageRequest.of(pageNumber, pageSize))
                .map(productDTOMapper);
    }

    public Map<String, Object> addProduct(ProductRequest productRequest) {

        Product product = buildProduct(productRequest);
        productRepository.save(product);
        return generateResponse(CREATED, PRODUCT_ADDED);
    }

    private Product buildProduct(ProductRequest productRequest) {

        UUID storeId = productRequest.getStoreId();
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new BackendException(STORE_NOT_FOUND));
        return new Product(productRequest, store);
    }
}