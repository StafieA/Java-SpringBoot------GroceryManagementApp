package controllers;




import data.Rating;
import entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.ProductService;


import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public Product createProduct(@RequestParam int id, @RequestParam String name, @RequestParam BigDecimal price,
                                 @RequestParam Rating rating, @RequestParam(required = false) LocalDate bestBefore) {
        if (bestBefore != null) {
            return productService.createProduct(id, name, price, rating, bestBefore);
        } else {
            return productService.createProduct(id, name, price, rating);
        }
    }

    @PostMapping("/product/{id}/review")
    public void reviewProduct(@PathVariable int id, @RequestParam Rating rating, @RequestParam String comment) {
        productService.reviewProduct(id, rating, comment);
    }

    @GetMapping("/product/{id}")
    public void printProductReport(@PathVariable int id) {
        productService.printProductReport(id);
    }

    @GetMapping("/products")
    public void printProducts() {
        productService.printProducts();
    }
}

