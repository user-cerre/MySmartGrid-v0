package grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: gestionDemanda.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GestorDemandaGrpc {

  private GestorDemandaGrpc() {}

  public static final String SERVICE_NAME = "GestorDemanda";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.GestorDemandaOuterClass.ConsumoRequest,
      grpc.GestorDemandaOuterClass.ClasificacionReply> getClasificarDemandaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "clasificarDemanda",
      requestType = grpc.GestorDemandaOuterClass.ConsumoRequest.class,
      responseType = grpc.GestorDemandaOuterClass.ClasificacionReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.GestorDemandaOuterClass.ConsumoRequest,
      grpc.GestorDemandaOuterClass.ClasificacionReply> getClasificarDemandaMethod() {
    io.grpc.MethodDescriptor<grpc.GestorDemandaOuterClass.ConsumoRequest, grpc.GestorDemandaOuterClass.ClasificacionReply> getClasificarDemandaMethod;
    if ((getClasificarDemandaMethod = GestorDemandaGrpc.getClasificarDemandaMethod) == null) {
      synchronized (GestorDemandaGrpc.class) {
        if ((getClasificarDemandaMethod = GestorDemandaGrpc.getClasificarDemandaMethod) == null) {
          GestorDemandaGrpc.getClasificarDemandaMethod = getClasificarDemandaMethod =
              io.grpc.MethodDescriptor.<grpc.GestorDemandaOuterClass.ConsumoRequest, grpc.GestorDemandaOuterClass.ClasificacionReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "clasificarDemanda"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.GestorDemandaOuterClass.ConsumoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.GestorDemandaOuterClass.ClasificacionReply.getDefaultInstance()))
              .setSchemaDescriptor(new GestorDemandaMethodDescriptorSupplier("clasificarDemanda"))
              .build();
        }
      }
    }
    return getClasificarDemandaMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GestorDemandaStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GestorDemandaStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GestorDemandaStub>() {
        @java.lang.Override
        public GestorDemandaStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GestorDemandaStub(channel, callOptions);
        }
      };
    return GestorDemandaStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GestorDemandaBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GestorDemandaBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GestorDemandaBlockingStub>() {
        @java.lang.Override
        public GestorDemandaBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GestorDemandaBlockingStub(channel, callOptions);
        }
      };
    return GestorDemandaBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GestorDemandaFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GestorDemandaFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GestorDemandaFutureStub>() {
        @java.lang.Override
        public GestorDemandaFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GestorDemandaFutureStub(channel, callOptions);
        }
      };
    return GestorDemandaFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<grpc.GestorDemandaOuterClass.ConsumoRequest> clasificarDemanda(
        io.grpc.stub.StreamObserver<grpc.GestorDemandaOuterClass.ClasificacionReply> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getClasificarDemandaMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service GestorDemanda.
   */
  public static abstract class GestorDemandaImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return GestorDemandaGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service GestorDemanda.
   */
  public static final class GestorDemandaStub
      extends io.grpc.stub.AbstractAsyncStub<GestorDemandaStub> {
    private GestorDemandaStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GestorDemandaStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GestorDemandaStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.GestorDemandaOuterClass.ConsumoRequest> clasificarDemanda(
        io.grpc.stub.StreamObserver<grpc.GestorDemandaOuterClass.ClasificacionReply> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getClasificarDemandaMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service GestorDemanda.
   */
  public static final class GestorDemandaBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<GestorDemandaBlockingStub> {
    private GestorDemandaBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GestorDemandaBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GestorDemandaBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service GestorDemanda.
   */
  public static final class GestorDemandaFutureStub
      extends io.grpc.stub.AbstractFutureStub<GestorDemandaFutureStub> {
    private GestorDemandaFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GestorDemandaFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GestorDemandaFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_CLASIFICAR_DEMANDA = 0;

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
        case METHODID_CLASIFICAR_DEMANDA:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.clasificarDemanda(
              (io.grpc.stub.StreamObserver<grpc.GestorDemandaOuterClass.ClasificacionReply>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getClasificarDemandaMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              grpc.GestorDemandaOuterClass.ConsumoRequest,
              grpc.GestorDemandaOuterClass.ClasificacionReply>(
                service, METHODID_CLASIFICAR_DEMANDA)))
        .build();
  }

  private static abstract class GestorDemandaBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GestorDemandaBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.GestorDemandaOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GestorDemanda");
    }
  }

  private static final class GestorDemandaFileDescriptorSupplier
      extends GestorDemandaBaseDescriptorSupplier {
    GestorDemandaFileDescriptorSupplier() {}
  }

  private static final class GestorDemandaMethodDescriptorSupplier
      extends GestorDemandaBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GestorDemandaMethodDescriptorSupplier(String methodName) {
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
      synchronized (GestorDemandaGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GestorDemandaFileDescriptorSupplier())
              .addMethod(getClasificarDemandaMethod())
              .build();
        }
      }
    }
    return result;
  }
}
