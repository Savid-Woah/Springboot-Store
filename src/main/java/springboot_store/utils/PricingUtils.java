package springboot_store.utils;

import springboot_store.product.model.Product;

import java.util.Set;

public class PricingUtils {

    public static Double calculateSaleFinalPrice(Set<Product> products) {

        return products.stream()
                .mapToDouble(Product::getUnitPrice)
                .sum();
    }
}