package grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: GestorPagosEnergeticos.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GestorPagosGrpc {

  private GestorPagosGrpc() {}

  public static final String SERVICE_NAME = "GestorPagos";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.PagosOuterClass.PagoRequest,
      grpc.PagosOuterClass.PagoReply> getPagarMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "pagar",
      requestType = grpc.PagosOuterClass.PagoRequest.class,
      responseType = grpc.PagosOuterClass.PagoReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.PagosOuterClass.PagoRequest,
      grpc.PagosOuterClass.PagoReply> getPagarMethod() {
    io.grpc.MethodDescriptor<grpc.PagosOuterClass.PagoRequest, grpc.PagosOuterClass.PagoReply> getPagarMethod;
    if ((getPagarMethod = GestorPagosGrpc.getPagarMethod) == null) {
      synchronized (GestorPagosGrpc.class) {
        if ((getPagarMethod = GestorPagosGrpc.getPagarMethod) == null) {
          GestorPagosGrpc.getPagarMethod = getPagarMethod =
              io.grpc.MethodDescriptor.<grpc.PagosOuterClass.PagoRequest, grpc.PagosOuterClass.PagoReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "pagar"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.PagosOuterClass.PagoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.PagosOuterClass.PagoReply.getDefaultInstance()))
              .setSchemaDescriptor(new GestorPagosMethodDescriptorSupplier("pagar"))
              .build();
        }
      }
    }
    return getPagarMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.PagosOuterClass.ZonaRequest,
      grpc.PagosOuterClass.ZonaReply> getRechazadosPorZonaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rechazadosPorZona",
      requestType = grpc.PagosOuterClass.ZonaRequest.class,
      responseType = grpc.PagosOuterClass.ZonaReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.PagosOuterClass.ZonaRequest,
      grpc.PagosOuterClass.ZonaReply> getRechazadosPorZonaMethod() {
    io.grpc.MethodDescriptor<grpc.PagosOuterClass.ZonaRequest, grpc.PagosOuterClass.ZonaReply> getRechazadosPorZonaMethod;
    if ((getRechazadosPorZonaMethod = GestorPagosGrpc.getRechazadosPorZonaMethod) == null) {
      synchronized (GestorPagosGrpc.class) {
        if ((getRechazadosPorZonaMethod = GestorPagosGrpc.getRechazadosPorZonaMethod) == null) {
          GestorPagosGrpc.getRechazadosPorZonaMethod = getRechazadosPorZonaMethod =
              io.grpc.MethodDescriptor.<grpc.PagosOuterClass.ZonaRequest, grpc.PagosOuterClass.ZonaReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "rechazadosPorZona"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.PagosOuterClass.ZonaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.PagosOuterClass.ZonaReply.getDefaultInstance()))
              .setSchemaDescriptor(new GestorPagosMethodDescriptorSupplier("rechazadosPorZona"))
              .build();
        }
      }
    }
    return getRechazadosPorZonaMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GestorPagosStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GestorPagosStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GestorPagosStub>() {
        @java.lang.Override
        public GestorPagosStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GestorPagosStub(channel, callOptions);
        }
      };
    return GestorPagosStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GestorPagosBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GestorPagosBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GestorPagosBlockingStub>() {
        @java.lang.Override
        public GestorPagosBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GestorPagosBlockingStub(channel, callOptions);
        }
      };
    return GestorPagosBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GestorPagosFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GestorPagosFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GestorPagosFutureStub>() {
        @java.lang.Override
        public GestorPagosFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GestorPagosFutureStub(channel, callOptions);
        }
      };
    return GestorPagosFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void pagar(grpc.PagosOuterClass.PagoRequest request,
        io.grpc.stub.StreamObserver<grpc.PagosOuterClass.PagoReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPagarMethod(), responseObserver);
    }

    /**
     */
    default void rechazadosPorZona(grpc.PagosOuterClass.ZonaRequest request,
        io.grpc.stub.StreamObserver<grpc.PagosOuterClass.ZonaReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRechazadosPorZonaMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service GestorPagos.
   */
  public static abstract class GestorPagosImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return GestorPagosGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service GestorPagos.
   */
  public static final class GestorPagosStub
      extends io.grpc.stub.AbstractAsyncStub<GestorPagosStub> {
    private GestorPagosStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GestorPagosStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GestorPagosStub(channel, callOptions);
    }

    /**
     */
    public void pagar(grpc.PagosOuterClass.PagoRequest request,
        io.grpc.stub.StreamObserver<grpc.PagosOuterClass.PagoReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPagarMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rechazadosPorZona(grpc.PagosOuterClass.ZonaRequest request,
        io.grpc.stub.StreamObserver<grpc.PagosOuterClass.ZonaReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getRechazadosPorZonaMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service GestorPagos.
   */
  public static final class GestorPagosBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<GestorPagosBlockingStub> {
    private GestorPagosBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GestorPagosBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GestorPagosBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.PagosOuterClass.PagoReply pagar(grpc.PagosOuterClass.PagoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPagarMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<grpc.PagosOuterClass.ZonaReply> rechazadosPorZona(
        grpc.PagosOuterClass.ZonaRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getRechazadosPorZonaMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service GestorPagos.
   */
  public static final class GestorPagosFutureStub
      extends io.grpc.stub.AbstractFutureStub<GestorPagosFutureStub> {
    private GestorPagosFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GestorPagosFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GestorPagosFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.PagosOuterClass.PagoReply> pagar(
        grpc.PagosOuterClass.PagoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPagarMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PAGAR = 0;
  private static final int METHODID_RECHAZADOS_POR_ZONA = 1;

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
        case METHODID_PAGAR:
          serviceImpl.pagar((grpc.PagosOuterClass.PagoRequest) request,
              (io.grpc.stub.StreamObserver<grpc.PagosOuterClass.PagoReply>) responseObserver);
          break;
        case METHODID_RECHAZADOS_POR_ZONA:
          serviceImpl.rechazadosPorZona((grpc.PagosOuterClass.ZonaRequest) request,
              (io.grpc.stub.StreamObserver<grpc.PagosOuterClass.ZonaReply>) responseObserver);
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
          getPagarMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              grpc.PagosOuterClass.PagoRequest,
              grpc.PagosOuterClass.PagoReply>(
                service, METHODID_PAGAR)))
        .addMethod(
          getRechazadosPorZonaMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              grpc.PagosOuterClass.ZonaRequest,
              grpc.PagosOuterClass.ZonaReply>(
                service, METHODID_RECHAZADOS_POR_ZONA)))
        .build();
  }

  private static abstract class GestorPagosBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GestorPagosBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.PagosOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GestorPagos");
    }
  }

  private static final class GestorPagosFileDescriptorSupplier
      extends GestorPagosBaseDescriptorSupplier {
    GestorPagosFileDescriptorSupplier() {}
  }

  private static final class GestorPagosMethodDescriptorSupplier
      extends GestorPagosBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GestorPagosMethodDescriptorSupplier(String methodName) {
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
      synchronized (GestorPagosGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GestorPagosFileDescriptorSupplier())
              .addMethod(getPagarMethod())
              .addMethod(getRechazadosPorZonaMethod())
              .build();
        }
      }
    }
    return result;
  }
}
