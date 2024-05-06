package sr.grpc.server;

import java.io.ObjectInputFilter;
import java.util.Random;

import com.google.protobuf.DescriptorProtos;
import io.grpc.stub.StreamObserver;
import sr.grpc.gen.ConfigGrpc.ConfigImplBase;
import sr.grpc.gen.Empty;
import sr.grpc.gen.m1;

public class ConfigImpl extends ConfigImplBase {
    @Override
    public void test1(Empty request, StreamObserver<m1> responseObserver) {
        System.out.println("test1 called");
        Random rng = new Random();
        m1 response;
        if (rng.nextBoolean()) {
            int res = rng.nextInt();
            System.out.println("Returning " + res + " as field1");
            response = m1.newBuilder().setVal1(res).build();
        } else {
            System.out.println("Returning empty response");
            response = m1.newBuilder().build();
        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    @Override
    public void test2(m1 request, StreamObserver<Empty> responseObserver) {
        if(request.hasVal1()) {
            System.out.println("test2 called with val1 = " + request.getVal1());
        } else {
            System.out.println("test2 called with empty request");
        }
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}
