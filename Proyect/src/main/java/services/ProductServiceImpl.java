package services;


import com.productCatalog.Product;
import com.productCatalog.ProductServiceGrpc;
import com.productCatalog.User;
import io.grpc.stub.StreamObserver;

public class ProductServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {
    @Override
    public void addProduct(Product request, StreamObserver<Product> responseObserver) {
        super.addProduct(request, responseObserver);
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

