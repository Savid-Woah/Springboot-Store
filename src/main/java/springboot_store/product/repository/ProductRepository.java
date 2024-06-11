package springboot_store.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot_store.product.model.Product;
import springboot_store.sale.model.Sale;
import springboot_store.store.model.Store;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findAllByStore(Store store, Pageable pageable);
    Page<Product> findAllBySales(Sale sale, Pageable pageable);
}