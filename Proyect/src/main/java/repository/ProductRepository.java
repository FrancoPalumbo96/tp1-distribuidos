package repository;

import com.productCatalog.Product;
import javassist.NotFoundException;
import model.persist.product.PersistProduct;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends BaseRepository<PersistProduct> {

    private static ProductRepository ourInstance = new ProductRepository();

    private ProductRepository(){super();}

    public static ProductRepository getInstance() {
        return ourInstance;
    }

    @Override
    public List<PersistProduct> getAll() {
        super.refreshSession();
        List<PersistProduct> list = new ArrayList<>();
        try {
            list = session.createQuery("SELECT p FROM PersistProduct p", PersistProduct.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("List Size Is " + list.size());
        return list;
    }

    @Override
    public PersistProduct getById(Long id) throws NotFoundException {
        super.refreshSession();
        PersistProduct product = (PersistProduct) session.get(PersistProduct.class, id);
        if (product == null) throw new NotFoundException("Product Not Found");
        else return product;
    }


}
