package entities;


import data.Rateable;
import data.Rating;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

import static data.Rating.NOT_RATED;
import static java.math.RoundingMode.HALF_UP;

@Entity
public abstract class Product implements Rateable<Product> {

    public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Rating rating;

    public Product() {
        this(0, "No_Name", BigDecimal.ZERO);
    }

    Product(int id, String name, BigDecimal price) {
        this(id,name,price,NOT_RATED);
    }

    public Product(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Rating getRating() {
        return rating;
    }

    public BigDecimal getDiscount() {
        return price.multiply(DISCOUNT_RATE).setScale(2, HALF_UP);
    }

    @Override
    public abstract Product applyRating(Rating newRating);

    @Override
    public String toString() {
        return "Product " + id + " " + name + ", price " + price + ", Discount " + getDiscount() + ", Rating " + rating.getStars();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Product) {
            Product other = (Product) obj;
            return id == other.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

