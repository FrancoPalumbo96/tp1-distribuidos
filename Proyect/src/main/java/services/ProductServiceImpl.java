package services;


import com.productCatalog.Product;
import com.productCatalog.ProductServiceGrpc;
import com.productCatalog.User;
import io.grpc.stub.StreamObserver;
import model.persist.product.PersistProduct;
import model.persist.product.ProductFactory;
import repository.ProductRepository;

public class ProductServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {

    //Decide a good Injection Strategy (Maybe Singleton???)
    final ProductRepository productRepository = new ProductRepository();

    @Override
    public void addProduct(Product request, StreamObserver<Product> responseObserver) {
        final PersistProduct toPersist = ProductFactory.createPersistProductFromProduct(request);
        this.productRepository.save(toPersist);
        responseObserver.onNext(request);
    }

    @Override
    public void getWishlist(User request, StreamObserver<Product> responseObserver) {
        super.getWishlist(request, responseObserver);
    }

    @Override
    public void deleteProductFromWishlist(Product request, StreamObserver<Product> responseObserver) {
        super.deleteProductFromWishlist(request, responseObserver);
    }
}

