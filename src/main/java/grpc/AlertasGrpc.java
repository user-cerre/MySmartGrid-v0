package grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: Alertas.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AlertasGrpc {

  private AlertasGrpc() {}

  public static final String SERVICE_NAME = "Alertas";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.AlertasOuterClass.UmbralRequest,
      grpc.AlertasOuterClass.AlertaReply> getListarConsumosCriticosMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listarConsumosCriticos",
      requestType = grpc.AlertasOuterClass.UmbralRequest.class,
      responseType = grpc.AlertasOuterClass.AlertaReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.AlertasOuterClass.UmbralRequest,
      grpc.AlertasOuterClass.AlertaReply> getListarConsumosCriticosMethod() {
    io.grpc.MethodDescriptor<grpc.AlertasOuterClass.UmbralRequest, grpc.AlertasOuterClass.AlertaReply> getListarConsumosCriticosMethod;
    if ((getListarConsumosCriticosMethod = AlertasGrpc.getListarConsumosCriticosMethod) == null) {
      synchronized (AlertasGrpc.class) {
        if ((getListarConsumosCriticosMethod = AlertasGrpc.getListarConsumosCriticosMethod) == null) {
          AlertasGrpc.getListarConsumosCriticosMethod = getListarConsumosCriticosMethod =
              io.grpc.MethodDescriptor.<grpc.AlertasOuterClass.UmbralRequest, grpc.AlertasOuterClass.AlertaReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listarConsumosCriticos"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.AlertasOuterClass.UmbralRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.AlertasOuterClass.AlertaReply.getDefaultInstance()))
              .setSchemaDescriptor(new AlertasMethodDescriptorSupplier("listarConsumosCriticos"))
              .build();
        }
      }
    }
    return getListarConsumosCriticosMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AlertasStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AlertasStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AlertasStub>() {
        @java.lang.Override
        public AlertasStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AlertasStub(channel, callOptions);
        }
      };
    return AlertasStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AlertasBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AlertasBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AlertasBlockingStub>() {
        @java.lang.Override
        public AlertasBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AlertasBlockingStub(channel, callOptions);
        }
      };
    return AlertasBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AlertasFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AlertasFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AlertasFutureStub>() {
        @java.lang.Override
        public AlertasFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AlertasFutureStub(channel, callOptions);
        }
      };
    return AlertasFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Server Streaming: El servidor devuelve un flujo (stream)
     * </pre>
     */
    default void listarConsumosCriticos(grpc.AlertasOuterClass.UmbralRequest request,
        io.grpc.stub.StreamObserver<grpc.AlertasOuterClass.AlertaReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListarConsumosCriticosMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Alertas.
   */
  public static abstract class AlertasImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AlertasGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Alertas.
   */
  public static final class AlertasStub
      extends io.grpc.stub.AbstractAsyncStub<AlertasStub> {
    private AlertasStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AlertasStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AlertasStub(channel, callOptions);
    }

    /**
     * <pre>
     * Server Streaming: El servidor devuelve un flujo (stream)
     * </pre>
     */
    public void listarConsumosCriticos(grpc.AlertasOuterClass.UmbralRequest request,
        io.grpc.stub.StreamObserver<grpc.AlertasOuterClass.AlertaReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getListarConsumosCriticosMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Alertas.
   */
  public static final class AlertasBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AlertasBlockingStub> {
    private AlertasBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AlertasBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AlertasBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Server Streaming: El servidor devuelve un flujo (stream)
     * </pre>
     */
    public java.util.Iterator<grpc.AlertasOuterClass.AlertaReply> listarConsumosCriticos(
        grpc.AlertasOuterClass.UmbralRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getListarConsumosCriticosMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Alertas.
   */
  public static final class AlertasFutureStub
      extends io.grpc.stub.AbstractFutureStub<AlertasFutureStub> {
    private AlertasFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AlertasFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AlertasFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_LISTAR_CONSUMOS_CRITICOS = 0;

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
        case METHODID_LISTAR_CONSUMOS_CRITICOS:
          serviceImpl.listarConsumosCriticos((grpc.AlertasOuterClass.UmbralRequest) request,
              (io.grpc.stub.StreamObserver<grpc.AlertasOuterClass.AlertaReply>) responseObserver);
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
          getListarConsumosCriticosMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              grpc.AlertasOuterClass.UmbralRequest,
              grpc.AlertasOuterClass.AlertaReply>(
                service, METHODID_LISTAR_CONSUMOS_CRITICOS)))
        .build();
  }

  private static abstract class AlertasBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AlertasBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.AlertasOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Alertas");
    }
  }

  private static final class AlertasFileDescriptorSupplier
      extends AlertasBaseDescriptorSupplier {
    AlertasFileDescriptorSupplier() {}
  }

  private static final class AlertasMethodDescriptorSupplier
      extends AlertasBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AlertasMethodDescriptorSupplier(String methodName) {
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
      synchronized (AlertasGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AlertasFileDescriptorSupplier())
              .addMethod(getListarConsumosCriticosMethod())
              .build();
        }
      }
    }
    return result;
  }
}
