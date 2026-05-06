package grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: sumatorioZonal.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SumatorioZonalGrpc {

  private SumatorioZonalGrpc() {}

  public static final String SERVICE_NAME = "SumatorioZonal";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.SumatorioZonalProto.ConsumoZonalRequest,
      grpc.SumatorioZonalProto.SumaReply> getCalcularSumaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "calcularSuma",
      requestType = grpc.SumatorioZonalProto.ConsumoZonalRequest.class,
      responseType = grpc.SumatorioZonalProto.SumaReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.SumatorioZonalProto.ConsumoZonalRequest,
      grpc.SumatorioZonalProto.SumaReply> getCalcularSumaMethod() {
    io.grpc.MethodDescriptor<grpc.SumatorioZonalProto.ConsumoZonalRequest, grpc.SumatorioZonalProto.SumaReply> getCalcularSumaMethod;
    if ((getCalcularSumaMethod = SumatorioZonalGrpc.getCalcularSumaMethod) == null) {
      synchronized (SumatorioZonalGrpc.class) {
        if ((getCalcularSumaMethod = SumatorioZonalGrpc.getCalcularSumaMethod) == null) {
          SumatorioZonalGrpc.getCalcularSumaMethod = getCalcularSumaMethod =
              io.grpc.MethodDescriptor.<grpc.SumatorioZonalProto.ConsumoZonalRequest, grpc.SumatorioZonalProto.SumaReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "calcularSuma"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.SumatorioZonalProto.ConsumoZonalRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.SumatorioZonalProto.SumaReply.getDefaultInstance()))
              .setSchemaDescriptor(new SumatorioZonalMethodDescriptorSupplier("calcularSuma"))
              .build();
        }
      }
    }
    return getCalcularSumaMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SumatorioZonalStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SumatorioZonalStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SumatorioZonalStub>() {
        @java.lang.Override
        public SumatorioZonalStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SumatorioZonalStub(channel, callOptions);
        }
      };
    return SumatorioZonalStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SumatorioZonalBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SumatorioZonalBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SumatorioZonalBlockingStub>() {
        @java.lang.Override
        public SumatorioZonalBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SumatorioZonalBlockingStub(channel, callOptions);
        }
      };
    return SumatorioZonalBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SumatorioZonalFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SumatorioZonalFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SumatorioZonalFutureStub>() {
        @java.lang.Override
        public SumatorioZonalFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SumatorioZonalFutureStub(channel, callOptions);
        }
      };
    return SumatorioZonalFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<grpc.SumatorioZonalProto.ConsumoZonalRequest> calcularSuma(
        io.grpc.stub.StreamObserver<grpc.SumatorioZonalProto.SumaReply> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getCalcularSumaMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service SumatorioZonal.
   */
  public static abstract class SumatorioZonalImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return SumatorioZonalGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service SumatorioZonal.
   */
  public static final class SumatorioZonalStub
      extends io.grpc.stub.AbstractAsyncStub<SumatorioZonalStub> {
    private SumatorioZonalStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SumatorioZonalStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SumatorioZonalStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.SumatorioZonalProto.ConsumoZonalRequest> calcularSuma(
        io.grpc.stub.StreamObserver<grpc.SumatorioZonalProto.SumaReply> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getCalcularSumaMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service SumatorioZonal.
   */
  public static final class SumatorioZonalBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SumatorioZonalBlockingStub> {
    private SumatorioZonalBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SumatorioZonalBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SumatorioZonalBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service SumatorioZonal.
   */
  public static final class SumatorioZonalFutureStub
      extends io.grpc.stub.AbstractFutureStub<SumatorioZonalFutureStub> {
    private SumatorioZonalFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SumatorioZonalFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SumatorioZonalFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_CALCULAR_SUMA = 0;

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
        case METHODID_CALCULAR_SUMA:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.calcularSuma(
              (io.grpc.stub.StreamObserver<grpc.SumatorioZonalProto.SumaReply>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCalcularSumaMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              grpc.SumatorioZonalProto.ConsumoZonalRequest,
              grpc.SumatorioZonalProto.SumaReply>(
                service, METHODID_CALCULAR_SUMA)))
        .build();
  }

  private static abstract class SumatorioZonalBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SumatorioZonalBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.SumatorioZonalProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SumatorioZonal");
    }
  }

  private static final class SumatorioZonalFileDescriptorSupplier
      extends SumatorioZonalBaseDescriptorSupplier {
    SumatorioZonalFileDescriptorSupplier() {}
  }

  private static final class SumatorioZonalMethodDescriptorSupplier
      extends SumatorioZonalBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SumatorioZonalMethodDescriptorSupplier(String methodName) {
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
      synchronized (SumatorioZonalGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SumatorioZonalFileDescriptorSupplier())
              .addMethod(getCalcularSumaMethod())
              .build();
        }
      }
    }
    return result;
  }
}
