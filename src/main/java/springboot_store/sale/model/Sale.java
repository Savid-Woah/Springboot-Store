package springboot_store.sale.model;

import jakarta.persistence.*;
import lombok.*;
import springboot_store.product.model.Product;
import springboot_store.store.model.Store;

import java.util.Set;
import java.util.UUID;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Sale")
@Table(name = "sales")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "sale_id", updatable = false, nullable = false)
    private UUID saleId;

    @Column(name = "code", updatable = false, nullable = false)
    private String code;

    @Column(name = "final_price", updatable = false, nullable = false)
    private Double finalPrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", updatable = false, nullable = false)
    private Store store;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "sales_x_products",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;

    public Sale(Set<Product> products, Double finalPrice) {

        this.code = randomAlphanumeric(10);
        this.finalPrice = finalPrice;
        this.products = products;
    }
}