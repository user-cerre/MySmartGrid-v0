package grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: balanceador.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class BalanceadorGrpc {

  private BalanceadorGrpc() {}

  public static final String SERVICE_NAME = "Balanceador";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.BalanceadorOuterClass.EstadoBateria,
      grpc.BalanceadorOuterClass.InstruccionCarga> getAjustarCargaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ajustarCarga",
      requestType = grpc.BalanceadorOuterClass.EstadoBateria.class,
      responseType = grpc.BalanceadorOuterClass.InstruccionCarga.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.BalanceadorOuterClass.EstadoBateria,
      grpc.BalanceadorOuterClass.InstruccionCarga> getAjustarCargaMethod() {
    io.grpc.MethodDescriptor<grpc.BalanceadorOuterClass.EstadoBateria, grpc.BalanceadorOuterClass.InstruccionCarga> getAjustarCargaMethod;
    if ((getAjustarCargaMethod = BalanceadorGrpc.getAjustarCargaMethod) == null) {
      synchronized (BalanceadorGrpc.class) {
        if ((getAjustarCargaMethod = BalanceadorGrpc.getAjustarCargaMethod) == null) {
          BalanceadorGrpc.getAjustarCargaMethod = getAjustarCargaMethod =
              io.grpc.MethodDescriptor.<grpc.BalanceadorOuterClass.EstadoBateria, grpc.BalanceadorOuterClass.InstruccionCarga>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ajustarCarga"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.BalanceadorOuterClass.EstadoBateria.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.BalanceadorOuterClass.InstruccionCarga.getDefaultInstance()))
              .setSchemaDescriptor(new BalanceadorMethodDescriptorSupplier("ajustarCarga"))
              .build();
        }
      }
    }
    return getAjustarCargaMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BalanceadorStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BalanceadorStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BalanceadorStub>() {
        @java.lang.Override
        public BalanceadorStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BalanceadorStub(channel, callOptions);
        }
      };
    return BalanceadorStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BalanceadorBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BalanceadorBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BalanceadorBlockingStub>() {
        @java.lang.Override
        public BalanceadorBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BalanceadorBlockingStub(channel, callOptions);
        }
      };
    return BalanceadorBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BalanceadorFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BalanceadorFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BalanceadorFutureStub>() {
        @java.lang.Override
        public BalanceadorFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BalanceadorFutureStub(channel, callOptions);
        }
      };
    return BalanceadorFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Bidirectional: Flujo de ida y flujo de vuelta
     * </pre>
     */
    default io.grpc.stub.StreamObserver<grpc.BalanceadorOuterClass.EstadoBateria> ajustarCarga(
        io.grpc.stub.StreamObserver<grpc.BalanceadorOuterClass.InstruccionCarga> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getAjustarCargaMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Balanceador.
   */
  public static abstract class BalanceadorImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return BalanceadorGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Balanceador.
   */
  public static final class BalanceadorStub
      extends io.grpc.stub.AbstractAsyncStub<BalanceadorStub> {
    private BalanceadorStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BalanceadorStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BalanceadorStub(channel, callOptions);
    }

    /**
     * <pre>
     * Bidirectional: Flujo de ida y flujo de vuelta
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.BalanceadorOuterClass.EstadoBateria> ajustarCarga(
        io.grpc.stub.StreamObserver<grpc.BalanceadorOuterClass.InstruccionCarga> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getAjustarCargaMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Balanceador.
   */
  public static final class BalanceadorBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<BalanceadorBlockingStub> {
    private BalanceadorBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BalanceadorBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BalanceadorBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Balanceador.
   */
  public static final class BalanceadorFutureStub
      extends io.grpc.stub.AbstractFutureStub<BalanceadorFutureStub> {
    private BalanceadorFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BalanceadorFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BalanceadorFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_AJUSTAR_CARGA = 0;

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
        case METHODID_AJUSTAR_CARGA:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.ajustarCarga(
              (io.grpc.stub.StreamObserver<grpc.BalanceadorOuterClass.InstruccionCarga>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getAjustarCargaMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              grpc.BalanceadorOuterClass.EstadoBateria,
              grpc.BalanceadorOuterClass.InstruccionCarga>(
                service, METHODID_AJUSTAR_CARGA)))
        .build();
  }

  private static abstract class BalanceadorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BalanceadorBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.BalanceadorOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Balanceador");
    }
  }

  private static final class BalanceadorFileDescriptorSupplier
      extends BalanceadorBaseDescriptorSupplier {
    BalanceadorFileDescriptorSupplier() {}
  }

  private static final class BalanceadorMethodDescriptorSupplier
      extends BalanceadorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    BalanceadorMethodDescriptorSupplier(String methodName) {
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
      synchronized (BalanceadorGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BalanceadorFileDescriptorSupplier())
              .addMethod(getAjustarCargaMethod())
              .build();
        }
      }
    }
    return result;
  }
}
