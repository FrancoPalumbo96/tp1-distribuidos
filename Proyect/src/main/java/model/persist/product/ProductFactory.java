package model.persist.product;

import com.productCatalog.Product;

public class ProductFactory {
    public static PersistProduct createPersistProductFromProduct(Product product){
        return new PersistProduct(product.getName(), product.getPrice(), product.getDescription());}

    public static Product createProuctFromPersistProduct(PersistProduct persistProduct){
        Product product = Product.newBuilder()
                .setId(persistProduct.getId())
                .setName(persistProduct.getName())
                .setDescription(persistProduct.getDescription())
                .setPrice(persistProduct.getPrice())
                .build();
        return product;
    }
}
