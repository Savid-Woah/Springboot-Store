package springboot_store.product.model;

import jakarta.persistence.*;
import lombok.*;
import springboot_store.product.request.ProductRequest;
import springboot_store.sale.model.Sale;
import springboot_store.store.model.Store;

import java.util.Set;
import java.util.UUID;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Product")
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id", updatable = false, nullable = false)
    private UUID productId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", updatable = false, nullable = false)
    private String code;

    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<Sale> sales;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", updatable = false, nullable = false)
    private Store store;

    public Product(ProductRequest productRequest, Store store) {

        this.name = productRequest.getName();
        this.code = randomAlphanumeric(10);
        this.unitPrice = productRequest.getUnitPrice();
        this.store = store;
    }
}