package springboot_store.store.mapper;

import org.springframework.stereotype.Component;
import springboot_store.store.dto.StoreDTO;
import springboot_store.store.model.Store;

import java.util.function.Function;

@Component
public class StoreDTOMapper implements Function<Store, StoreDTO> {
    @Override
    public StoreDTO apply(Store store) {
        return new StoreDTO(
                store.getStoreId(),
                store.getName(),
                store.getCode()
        );
    }
}