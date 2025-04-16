package stafie.app.services;



import stafie.app.data.Rating;
import stafie.app.entities.Drink;
import stafie.app.entities.Food;
import stafie.app.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stafie.app.repo.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(int id, String name, BigDecimal price, Rating rating) {
        Product product = new Drink(id, name, price, rating);
        return productRepository.save(product);
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        Product product = new Food(id, name, price, rating, bestBefore);
        return productRepository.save(product);
    }

    public void reviewProduct(int productId, Rating rating, String comment) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        product.applyRating(rating);
        productRepository.save(product);
    }

    public void printProductReport(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        System.out.println(product.toString());
    }

    public void printProducts() {
        productRepository.findAll().forEach(product -> System.out.println(product.toString()));
    }
}

