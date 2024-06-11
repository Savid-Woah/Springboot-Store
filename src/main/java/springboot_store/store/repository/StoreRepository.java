package springboot_store.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot_store.store.model.Store;

import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, UUID> {
}