package services;

import com.productCatalog.HealthServiceGrpc;
import com.productCatalog.PingRequest;
import com.productCatalog.PingResponse;
import io.grpc.stub.StreamObserver;

public class HealthServiceImpl extends HealthServiceGrpc.HealthServiceImplBase {
    @Override
    public void ping(PingRequest request, StreamObserver<PingResponse> responseObserver) {
        responseObserver.onNext(PingResponse.newBuilder().build());
        responseObserver.onCompleted();
    }
}
