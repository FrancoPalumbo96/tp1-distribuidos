import com.productCatalog.Product;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import model.persist.product.PersistProduct;
import model.persist.product.ProductFactory;
import repository.ProductRepository;
import services.HealthServiceImpl;
import services.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class MainServer {

    private static void dbStartUp(){
        List<PersistProduct> defaultProducts = new ArrayList<>();
        defaultProducts.add(new PersistProduct("ApplePen", 54, "ApplePen - PineApplePen"));
        defaultProducts.add(new PersistProduct("Messi Shirt", 100, "awesome as Messi!"));
        defaultProducts.add(new PersistProduct("Sergio Ramos Shirt", 3, "Not So Awesome"));

        ProductRepository repo = ProductRepository.getInstance();
        defaultProducts.stream().forEach(prod -> repo.save(prod));
    }

    public static void main(String[] arg) {
        try {
            Server server = ServerBuilder.forPort(50051)
                    .addService(new ProductServiceImpl())
                    .addService(new HealthServiceImpl())
                    .build();
            server.start();
            System.out.println("Server has started ========");
            System.out.println("STARTTING HIBERNATE");
            dbStartUp();
            System.out.println("STARTUP FINISHED, WAITING FOR REQUESTS");
            server.awaitTermination();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
