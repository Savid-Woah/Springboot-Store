package springboot_store.sale.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot_store.sale.model.Sale;
import springboot_store.store.model.Store;

import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
    Page<Sale> findAllByStore(Store store, Pageable pageable);
}