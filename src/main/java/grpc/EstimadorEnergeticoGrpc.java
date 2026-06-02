package grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: EstimarTiempo.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class EstimadorEnergeticoGrpc {

  private EstimadorEnergeticoGrpc() {}

  public static final String SERVICE_NAME = "EstimadorEnergetico";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.EstimadorOuterClass.ConsumoRequest,
      grpc.EstimadorOuterClass.EstimacionReply> getEstimarTiempoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "estimarTiempo",
      requestType = grpc.EstimadorOuterClass.ConsumoRequest.class,
      responseType = grpc.EstimadorOuterClass.EstimacionReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.EstimadorOuterClass.ConsumoRequest,
      grpc.EstimadorOuterClass.EstimacionReply> getEstimarTiempoMethod() {
    io.grpc.MethodDescriptor<grpc.EstimadorOuterClass.ConsumoRequest, grpc.EstimadorOuterClass.EstimacionReply> getEstimarTiempoMethod;
    if ((getEstimarTiempoMethod = EstimadorEnergeticoGrpc.getEstimarTiempoMethod) == null) {
      synchronized (EstimadorEnergeticoGrpc.class) {
        if ((getEstimarTiempoMethod = EstimadorEnergeticoGrpc.getEstimarTiempoMethod) == null) {
          EstimadorEnergeticoGrpc.getEstimarTiempoMethod = getEstimarTiempoMethod =
              io.grpc.MethodDescriptor.<grpc.EstimadorOuterClass.ConsumoRequest, grpc.EstimadorOuterClass.EstimacionReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "estimarTiempo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.EstimadorOuterClass.ConsumoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.EstimadorOuterClass.EstimacionReply.getDefaultInstance()))
              .setSchemaDescriptor(new EstimadorEnergeticoMethodDescriptorSupplier("estimarTiempo"))
              .build();
        }
      }
    }
    return getEstimarTiempoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EstimadorEnergeticoStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EstimadorEnergeticoStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EstimadorEnergeticoStub>() {
        @java.lang.Override
        public EstimadorEnergeticoStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EstimadorEnergeticoStub(channel, callOptions);
        }
      };
    return EstimadorEnergeticoStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EstimadorEnergeticoBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EstimadorEnergeticoBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EstimadorEnergeticoBlockingStub>() {
        @java.lang.Override
        public EstimadorEnergeticoBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EstimadorEnergeticoBlockingStub(channel, callOptions);
        }
      };
    return EstimadorEnergeticoBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EstimadorEnergeticoFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EstimadorEnergeticoFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EstimadorEnergeticoFutureStub>() {
        @java.lang.Override
        public EstimadorEnergeticoFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EstimadorEnergeticoFutureStub(channel, callOptions);
        }
      };
    return EstimadorEnergeticoFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<grpc.EstimadorOuterClass.ConsumoRequest> estimarTiempo(
        io.grpc.stub.StreamObserver<grpc.EstimadorOuterClass.EstimacionReply> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getEstimarTiempoMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service EstimadorEnergetico.
   */
  public static abstract class EstimadorEnergeticoImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return EstimadorEnergeticoGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service EstimadorEnergetico.
   */
  public static final class EstimadorEnergeticoStub
      extends io.grpc.stub.AbstractAsyncStub<EstimadorEnergeticoStub> {
    private EstimadorEnergeticoStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EstimadorEnergeticoStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EstimadorEnergeticoStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.EstimadorOuterClass.ConsumoRequest> estimarTiempo(
        io.grpc.stub.StreamObserver<grpc.EstimadorOuterClass.EstimacionReply> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getEstimarTiempoMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service EstimadorEnergetico.
   */
  public static final class EstimadorEnergeticoBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<EstimadorEnergeticoBlockingStub> {
    private EstimadorEnergeticoBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EstimadorEnergeticoBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EstimadorEnergeticoBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service EstimadorEnergetico.
   */
  public static final class EstimadorEnergeticoFutureStub
      extends io.grpc.stub.AbstractFutureStub<EstimadorEnergeticoFutureStub> {
    private EstimadorEnergeticoFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EstimadorEnergeticoFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EstimadorEnergeticoFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_ESTIMAR_TIEMPO = 0;

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
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ESTIMAR_TIEMPO:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.estimarTiempo(
              (io.grpc.stub.StreamObserver<grpc.EstimadorOuterClass.EstimacionReply>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getEstimarTiempoMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              grpc.EstimadorOuterClass.ConsumoRequest,
              grpc.EstimadorOuterClass.EstimacionReply>(
                service, METHODID_ESTIMAR_TIEMPO)))
        .build();
  }

  private static abstract class EstimadorEnergeticoBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EstimadorEnergeticoBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.EstimadorOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EstimadorEnergetico");
    }
  }

  private static final class EstimadorEnergeticoFileDescriptorSupplier
      extends EstimadorEnergeticoBaseDescriptorSupplier {
    EstimadorEnergeticoFileDescriptorSupplier() {}
  }

  private static final class EstimadorEnergeticoMethodDescriptorSupplier
      extends EstimadorEnergeticoBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EstimadorEnergeticoMethodDescriptorSupplier(String methodName) {
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
      synchronized (EstimadorEnergeticoGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EstimadorEnergeticoFileDescriptorSupplier())
              .addMethod(getEstimarTiempoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
