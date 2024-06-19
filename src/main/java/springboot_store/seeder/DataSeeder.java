package springboot_store.seeder;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import springboot_store.product.model.Product;
import springboot_store.product.repository.ProductRepository;
import springboot_store.sale.model.Sale;
import springboot_store.sale.repository.SaleRepository;
import springboot_store.security.user.enums.Role;
import springboot_store.security.user.model.User;
import springboot_store.security.user.repository.UserRepository;
import springboot_store.store.model.Store;
import springboot_store.store.repository.StoreRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static springboot_store.utils.PricingUtils.calculateSaleFinalPrice;

@Component
@Transactional
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        seedData();
    }

    private void seedData() {

        Store store = seedStore();
        Set<Product> products = seedProducts(store);
        seedSale(store, products);
    }

    private Store seedStore() {

        User user = User
                .builder()
                .email("store@gmail.com")
                .password(passwordEncoder.encode("store444"))
                .role(Role.CLIENT)
                .build();

        User savedUser = userRepository.save(user);

        Store store = Store
                .builder()
                .name("Store")
                .code(randomAlphanumeric(4))
                .user(savedUser)
                .build();

        return storeRepository.save(store);
    }

    private Set<Product> seedProducts(Store store) {

        Map<String, Double> productsToCreate = new HashMap<>();
        productsToCreate.put("Chips", 3000.0);
        productsToCreate.put("Brownie", 5000.0);
        productsToCreate.put("Coca-Cola", 3500.0);

        Set<Product> savedProducts = new HashSet<>();

        productsToCreate.forEach((name, price) -> {

            Product product = Product
                    .builder()
                    .name(name)
                    .code(randomAlphanumeric(4))
                    .unitPrice(price)
                    .store(store)
                    .build();

            Product savedProduct = productRepository.save(product);

            savedProducts.add(savedProduct);
        });

        return savedProducts;
    }

    private void seedSale(Store store, Set<Product> products) {

        Double finalPrice = calculateSaleFinalPrice(products);

        Sale sale = Sale
                .builder()
                .code(randomAlphanumeric(4))
                .store(store)
                .products(products)
                .finalPrice(finalPrice)
                .build();

        saleRepository.save(sale);
    }
}