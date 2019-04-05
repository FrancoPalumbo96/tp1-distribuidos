package model.persist;

import com.productCatalog.Product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PersistProduct {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private float price;

    private String description;

    private PersistProduct(String name, float price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    //Hibernate Constructor:
    public PersistProduct(){}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static PersistProduct createFromProduct(Product product){return new PersistProduct(product.getName(), product.getPrice(), product.getDescription());}
}
