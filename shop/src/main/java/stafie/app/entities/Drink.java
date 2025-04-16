package stafie.app.entities;



import stafie.app.data.Rating;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class Drink extends Product {

    public Drink(int id, String name, BigDecimal price, Rating rating) {
        super(id, name, price, rating);
    }

    @Override
    public Product applyRating(Rating newRating) {
        return new Drink(getId(), getName(), getPrice(), newRating);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

