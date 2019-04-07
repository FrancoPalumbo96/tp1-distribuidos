import com.productCatalog.Product;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import model.persist.product.PersistProduct;
import model.persist.product.ProductFactory;
import repository.ProductRepository;
import services.ProductServiceImpl;

public class MainServer {


    public static void main(String[] arg) {
        try {
            Server server = ServerBuilder.forPort(50051)
                    .addService(new ProductServiceImpl())
                    .build();
            server.start();
            System.out.println("Server has started ========");
            System.out.println("STARTTING HIBERNATE");
            Product product = Product.newBuilder().setName("ApplePen")
                    .setPrice(0.5f)
                    .setDescription("ApplePen - PineApple Pen")
                    .setId(2)
                    .build();
            ProductRepository.getInstance().save(ProductFactory.createPersistProductFromProduct(product));
            System.out.println("FINISHED");
            server.awaitTermination();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
