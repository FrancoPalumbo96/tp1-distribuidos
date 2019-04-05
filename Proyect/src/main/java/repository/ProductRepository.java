package repository;

import javassist.NotFoundException;
import model.persist.product.PersistProduct;

public class ProductRepository extends BaseRepository<PersistProduct> {

    @Override
    public PersistProduct getById(Long id) throws NotFoundException {
        super.refreshSession();
        PersistProduct product = (PersistProduct) session.get(PersistProduct.class, id);
        if (product == null) throw new NotFoundException("Product Not Found");
        else return product;
    }
}
