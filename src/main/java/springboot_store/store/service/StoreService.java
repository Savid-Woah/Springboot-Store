package springboot_store.store.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot_store.security.user.model.User;
import springboot_store.store.model.Store;
import springboot_store.store.repository.StoreRepository;
import springboot_store.store.request.StoreRequest;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public void createStore(StoreRequest storeRequest, User user) {

        Store store = new Store(storeRequest, user);
        storeRepository.save(store);
    }
}