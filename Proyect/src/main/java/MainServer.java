import io.grpc.Server;
import io.grpc.ServerBuilder;
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
