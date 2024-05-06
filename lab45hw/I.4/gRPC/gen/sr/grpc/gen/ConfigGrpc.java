package sr.grpc.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: calculator.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ConfigGrpc {

  private ConfigGrpc() {}

  public static final java.lang.String SERVICE_NAME = "calculator.Config";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.Empty,
      sr.grpc.gen.m1> getTest1Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "test1",
      requestType = sr.grpc.gen.Empty.class,
      responseType = sr.grpc.gen.m1.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.Empty,
      sr.grpc.gen.m1> getTest1Method() {
    io.grpc.MethodDescriptor<sr.grpc.gen.Empty, sr.grpc.gen.m1> getTest1Method;
    if ((getTest1Method = ConfigGrpc.getTest1Method) == null) {
      synchronized (ConfigGrpc.class) {
        if ((getTest1Method = ConfigGrpc.getTest1Method) == null) {
          ConfigGrpc.getTest1Method = getTest1Method =
              io.grpc.MethodDescriptor.<sr.grpc.gen.Empty, sr.grpc.gen.m1>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "test1"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.m1.getDefaultInstance()))
              .setSchemaDescriptor(new ConfigMethodDescriptorSupplier("test1"))
              .build();
        }
      }
    }
    return getTest1Method;
  }

  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.m1,
      sr.grpc.gen.Empty> getTest2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "test2",
      requestType = sr.grpc.gen.m1.class,
      responseType = sr.grpc.gen.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.m1,
      sr.grpc.gen.Empty> getTest2Method() {
    io.grpc.MethodDescriptor<sr.grpc.gen.m1, sr.grpc.gen.Empty> getTest2Method;
    if ((getTest2Method = ConfigGrpc.getTest2Method) == null) {
      synchronized (ConfigGrpc.class) {
        if ((getTest2Method = ConfigGrpc.getTest2Method) == null) {
          ConfigGrpc.getTest2Method = getTest2Method =
              io.grpc.MethodDescriptor.<sr.grpc.gen.m1, sr.grpc.gen.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "test2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.m1.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ConfigMethodDescriptorSupplier("test2"))
              .build();
        }
      }
    }
    return getTest2Method;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ConfigStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ConfigStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ConfigStub>() {
        @java.lang.Override
        public ConfigStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ConfigStub(channel, callOptions);
        }
      };
    return ConfigStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ConfigBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ConfigBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ConfigBlockingStub>() {
        @java.lang.Override
        public ConfigBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ConfigBlockingStub(channel, callOptions);
        }
      };
    return ConfigBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ConfigFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ConfigFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ConfigFutureStub>() {
        @java.lang.Override
        public ConfigFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ConfigFutureStub(channel, callOptions);
        }
      };
    return ConfigFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void test1(sr.grpc.gen.Empty request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.m1> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTest1Method(), responseObserver);
    }

    /**
     */
    default void test2(sr.grpc.gen.m1 request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTest2Method(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Config.
   */
  public static abstract class ConfigImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ConfigGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Config.
   */
  public static final class ConfigStub
      extends io.grpc.stub.AbstractAsyncStub<ConfigStub> {
    private ConfigStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConfigStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ConfigStub(channel, callOptions);
    }

    /**
     */
    public void test1(sr.grpc.gen.Empty request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.m1> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTest1Method(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void test2(sr.grpc.gen.m1 request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTest2Method(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Config.
   */
  public static final class ConfigBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ConfigBlockingStub> {
    private ConfigBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConfigBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ConfigBlockingStub(channel, callOptions);
    }

    /**
     */
    public sr.grpc.gen.m1 test1(sr.grpc.gen.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTest1Method(), getCallOptions(), request);
    }

    /**
     */
    public sr.grpc.gen.Empty test2(sr.grpc.gen.m1 request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTest2Method(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Config.
   */
  public static final class ConfigFutureStub
      extends io.grpc.stub.AbstractFutureStub<ConfigFutureStub> {
    private ConfigFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConfigFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ConfigFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.grpc.gen.m1> test1(
        sr.grpc.gen.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTest1Method(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.grpc.gen.Empty> test2(
        sr.grpc.gen.m1 request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTest2Method(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TEST1 = 0;
  private static final int METHODID_TEST2 = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_TEST1:
          serviceImpl.test1((sr.grpc.gen.Empty) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.m1>) responseObserver);
          break;
        case METHODID_TEST2:
          serviceImpl.test2((sr.grpc.gen.m1) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getTest1Method(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sr.grpc.gen.Empty,
              sr.grpc.gen.m1>(
                service, METHODID_TEST1)))
        .addMethod(
          getTest2Method(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sr.grpc.gen.m1,
              sr.grpc.gen.Empty>(
                service, METHODID_TEST2)))
        .build();
  }

  private static abstract class ConfigBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ConfigBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sr.grpc.gen.CalculatorProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Config");
    }
  }

  private static final class ConfigFileDescriptorSupplier
      extends ConfigBaseDescriptorSupplier {
    ConfigFileDescriptorSupplier() {}
  }

  private static final class ConfigMethodDescriptorSupplier
      extends ConfigBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ConfigMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ConfigGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ConfigFileDescriptorSupplier())
              .addMethod(getTest1Method())
              .addMethod(getTest2Method())
              .build();
        }
      }
    }
    return result;
  }
}
