package grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: ControlDeConsumosTramitados.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ControlDeConsumosTramitadosGrpc {

  private ControlDeConsumosTramitadosGrpc() {}

  public static final String SERVICE_NAME = "ControlDeConsumosTramitados";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.RegistroOuterClass.RegistroRequest,
      grpc.RegistroOuterClass.RegistroReply> getRegistrarConsumoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "registrarConsumo",
      requestType = grpc.RegistroOuterClass.RegistroRequest.class,
      responseType = grpc.RegistroOuterClass.RegistroReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.RegistroOuterClass.RegistroRequest,
      grpc.RegistroOuterClass.RegistroReply> getRegistrarConsumoMethod() {
    io.grpc.MethodDescriptor<grpc.RegistroOuterClass.RegistroRequest, grpc.RegistroOuterClass.RegistroReply> getRegistrarConsumoMethod;
    if ((getRegistrarConsumoMethod = ControlDeConsumosTramitadosGrpc.getRegistrarConsumoMethod) == null) {
      synchronized (ControlDeConsumosTramitadosGrpc.class) {
        if ((getRegistrarConsumoMethod = ControlDeConsumosTramitadosGrpc.getRegistrarConsumoMethod) == null) {
          ControlDeConsumosTramitadosGrpc.getRegistrarConsumoMethod = getRegistrarConsumoMethod =
              io.grpc.MethodDescriptor.<grpc.RegistroOuterClass.RegistroRequest, grpc.RegistroOuterClass.RegistroReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "registrarConsumo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.RegistroOuterClass.RegistroRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.RegistroOuterClass.RegistroReply.getDefaultInstance()))
              .setSchemaDescriptor(new ControlDeConsumosTramitadosMethodDescriptorSupplier("registrarConsumo"))
              .build();
        }
      }
    }
    return getRegistrarConsumoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.RegistroOuterClass.TramitadosRequest,
      grpc.RegistroOuterClass.TramitadosReply> getObtenerConsumosTramitadosMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "obtenerConsumosTramitados",
      requestType = grpc.RegistroOuterClass.TramitadosRequest.class,
      responseType = grpc.RegistroOuterClass.TramitadosReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.RegistroOuterClass.TramitadosRequest,
      grpc.RegistroOuterClass.TramitadosReply> getObtenerConsumosTramitadosMethod() {
    io.grpc.MethodDescriptor<grpc.RegistroOuterClass.TramitadosRequest, grpc.RegistroOuterClass.TramitadosReply> getObtenerConsumosTramitadosMethod;
    if ((getObtenerConsumosTramitadosMethod = ControlDeConsumosTramitadosGrpc.getObtenerConsumosTramitadosMethod) == null) {
      synchronized (ControlDeConsumosTramitadosGrpc.class) {
        if ((getObtenerConsumosTramitadosMethod = ControlDeConsumosTramitadosGrpc.getObtenerConsumosTramitadosMethod) == null) {
          ControlDeConsumosTramitadosGrpc.getObtenerConsumosTramitadosMethod = getObtenerConsumosTramitadosMethod =
              io.grpc.MethodDescriptor.<grpc.RegistroOuterClass.TramitadosRequest, grpc.RegistroOuterClass.TramitadosReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "obtenerConsumosTramitados"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.RegistroOuterClass.TramitadosRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.RegistroOuterClass.TramitadosReply.getDefaultInstance()))
              .setSchemaDescriptor(new ControlDeConsumosTramitadosMethodDescriptorSupplier("obtenerConsumosTramitados"))
              .build();
        }
      }
    }
    return getObtenerConsumosTramitadosMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ControlDeConsumosTramitadosStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ControlDeConsumosTramitadosStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ControlDeConsumosTramitadosStub>() {
        @java.lang.Override
        public ControlDeConsumosTramitadosStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ControlDeConsumosTramitadosStub(channel, callOptions);
        }
      };
    return ControlDeConsumosTramitadosStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ControlDeConsumosTramitadosBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ControlDeConsumosTramitadosBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ControlDeConsumosTramitadosBlockingStub>() {
        @java.lang.Override
        public ControlDeConsumosTramitadosBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ControlDeConsumosTramitadosBlockingStub(channel, callOptions);
        }
      };
    return ControlDeConsumosTramitadosBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ControlDeConsumosTramitadosFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ControlDeConsumosTramitadosFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ControlDeConsumosTramitadosFutureStub>() {
        @java.lang.Override
        public ControlDeConsumosTramitadosFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ControlDeConsumosTramitadosFutureStub(channel, callOptions);
        }
      };
    return ControlDeConsumosTramitadosFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void registrarConsumo(grpc.RegistroOuterClass.RegistroRequest request,
        io.grpc.stub.StreamObserver<grpc.RegistroOuterClass.RegistroReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegistrarConsumoMethod(), responseObserver);
    }

    /**
     */
    default void obtenerConsumosTramitados(grpc.RegistroOuterClass.TramitadosRequest request,
        io.grpc.stub.StreamObserver<grpc.RegistroOuterClass.TramitadosReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getObtenerConsumosTramitadosMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ControlDeConsumosTramitados.
   */
  public static abstract class ControlDeConsumosTramitadosImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ControlDeConsumosTramitadosGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ControlDeConsumosTramitados.
   */
  public static final class ControlDeConsumosTramitadosStub
      extends io.grpc.stub.AbstractAsyncStub<ControlDeConsumosTramitadosStub> {
    private ControlDeConsumosTramitadosStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ControlDeConsumosTramitadosStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ControlDeConsumosTramitadosStub(channel, callOptions);
    }

    /**
     */
    public void registrarConsumo(grpc.RegistroOuterClass.RegistroRequest request,
        io.grpc.stub.StreamObserver<grpc.RegistroOuterClass.RegistroReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegistrarConsumoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void obtenerConsumosTramitados(grpc.RegistroOuterClass.TramitadosRequest request,
        io.grpc.stub.StreamObserver<grpc.RegistroOuterClass.TramitadosReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getObtenerConsumosTramitadosMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ControlDeConsumosTramitados.
   */
  public static final class ControlDeConsumosTramitadosBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ControlDeConsumosTramitadosBlockingStub> {
    private ControlDeConsumosTramitadosBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ControlDeConsumosTramitadosBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ControlDeConsumosTramitadosBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.RegistroOuterClass.RegistroReply registrarConsumo(grpc.RegistroOuterClass.RegistroRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegistrarConsumoMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<grpc.RegistroOuterClass.TramitadosReply> obtenerConsumosTramitados(
        grpc.RegistroOuterClass.TramitadosRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getObtenerConsumosTramitadosMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ControlDeConsumosTramitados.
   */
  public static final class ControlDeConsumosTramitadosFutureStub
      extends io.grpc.stub.AbstractFutureStub<ControlDeConsumosTramitadosFutureStub> {
    private ControlDeConsumosTramitadosFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ControlDeConsumosTramitadosFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ControlDeConsumosTramitadosFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.RegistroOuterClass.RegistroReply> registrarConsumo(
        grpc.RegistroOuterClass.RegistroRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegistrarConsumoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTRAR_CONSUMO = 0;
  private static final int METHODID_OBTENER_CONSUMOS_TRAMITADOS = 1;

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
        case METHODID_REGISTRAR_CONSUMO:
          serviceImpl.registrarConsumo((grpc.RegistroOuterClass.RegistroRequest) request,
              (io.grpc.stub.StreamObserver<grpc.RegistroOuterClass.RegistroReply>) responseObserver);
          break;
        case METHODID_OBTENER_CONSUMOS_TRAMITADOS:
          serviceImpl.obtenerConsumosTramitados((grpc.RegistroOuterClass.TramitadosRequest) request,
              (io.grpc.stub.StreamObserver<grpc.RegistroOuterClass.TramitadosReply>) responseObserver);
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
          getRegistrarConsumoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              grpc.RegistroOuterClass.RegistroRequest,
              grpc.RegistroOuterClass.RegistroReply>(
                service, METHODID_REGISTRAR_CONSUMO)))
        .addMethod(
          getObtenerConsumosTramitadosMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              grpc.RegistroOuterClass.TramitadosRequest,
              grpc.RegistroOuterClass.TramitadosReply>(
                service, METHODID_OBTENER_CONSUMOS_TRAMITADOS)))
        .build();
  }

  private static abstract class ControlDeConsumosTramitadosBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ControlDeConsumosTramitadosBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.RegistroOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ControlDeConsumosTramitados");
    }
  }

  private static final class ControlDeConsumosTramitadosFileDescriptorSupplier
      extends ControlDeConsumosTramitadosBaseDescriptorSupplier {
    ControlDeConsumosTramitadosFileDescriptorSupplier() {}
  }

  private static final class ControlDeConsumosTramitadosMethodDescriptorSupplier
      extends ControlDeConsumosTramitadosBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ControlDeConsumosTramitadosMethodDescriptorSupplier(String methodName) {
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
      synchronized (ControlDeConsumosTramitadosGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ControlDeConsumosTramitadosFileDescriptorSupplier())
              .addMethod(getRegistrarConsumoMethod())
              .addMethod(getObtenerConsumosTramitadosMethod())
              .build();
        }
      }
    }
    return result;
  }
}
