import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import services.ProductServiceImpl;

public class MainServer {


    public static void main(String[] arg) {
        try {
            Server server = ServerBuilder.forPort(8080)
                    .addService(new ProductServiceImpl())
                    .build();
            server.start();
            System.out.println("Server has started ========");

            server.awaitTermination();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
