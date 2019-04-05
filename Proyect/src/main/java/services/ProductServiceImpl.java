package services;


import com.productCatalog.Product;
import com.productCatalog.ProductID;
import com.productCatalog.ProductServiceGrpc;
import com.productCatalog.User;
import io.grpc.stub.StreamObserver;
import javassist.NotFoundException;
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
        responseObserver.onCompleted();
    }

    @Override
    public void getWishlist(User request, StreamObserver<Product> responseObserver) {
        super.getWishlist(request, responseObserver);
    }

    @Override
    public void deleteProductFromWishlist(Product request, StreamObserver<Product> responseObserver) {
        super.deleteProductFromWishlist(request, responseObserver);
    }

    @Override
    public void getProductInfo(ProductID request, StreamObserver<Product> responseObserver) {
        if (request.getId() == 1L){
            responseObserver.onNext(Product.newBuilder().setId(1).setPrice(0.2f).setName("Masu").setDescription("Masu").build());
            responseObserver.onCompleted();
            return;
        }

        try {
            final PersistProduct persistProduct = this.productRepository.getById(request.getId());
            final Product product = ProductFactory.createProuctFromPersistProduct(persistProduct);
            responseObserver.onNext(product);
            responseObserver.onCompleted();
        } catch (NotFoundException e) {
            e.printStackTrace();
            responseObserver.onError(e);
        }

    }
}

