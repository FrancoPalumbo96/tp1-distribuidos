package services;


import com.productCatalog.*;
import io.grpc.stub.StreamObserver;
import javassist.NotFoundException;
import model.persist.product.PersistProduct;
import model.persist.product.ProductFactory;
import repository.ProductRepository;

public class ProductServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {

    //Decide a good Injection Strategy (Maybe Singleton???)
    final ProductRepository productRepository = ProductRepository.getInstance();

    @Override
    public void addProduct(CreateProductRequest request, StreamObserver<Product> responseObserver) {
        final PersistProduct toSave = new PersistProduct(request.getName(), request.getPrice(), request.getDescription());
        this.productRepository.save(toSave);
        Product product = ProductFactory.createProuctFromPersistProduct(toSave);
        responseObserver.onNext(product);
        responseObserver.onCompleted();
    }

    @Override
    public void getProductInfo(ProductID request, StreamObserver<Product> responseObserver) {
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

