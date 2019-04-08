package repository;

import com.productCatalog.Product;
import javassist.NotFoundException;
import model.persist.product.PersistProduct;

public class ProductRepository extends BaseRepository<PersistProduct> {

    private static ProductRepository ourInstance = new ProductRepository();

    private ProductRepository(){super();}

    public static ProductRepository getInstance() {
        return ourInstance;
    }

    @Override
    public PersistProduct getById(Long id) throws NotFoundException {
        super.refreshSession();
        PersistProduct product = session.get(PersistProduct.class, id);
        if (product == null) throw new NotFoundException("Product Not Found");
        else return product;
    }
}
