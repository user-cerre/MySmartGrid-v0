package grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: Estadisticas.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class EstadisticasGrpc {

  private EstadisticasGrpc() {}

  public static final String SERVICE_NAME = "Estadisticas";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.EstadisticasOuterClass.DatoRequest,
      grpc.EstadisticasOuterClass.MediaReply> getCalcularMediaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "calcularMedia",
      requestType = grpc.EstadisticasOuterClass.DatoRequest.class,
      responseType = grpc.EstadisticasOuterClass.MediaReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.EstadisticasOuterClass.DatoRequest,
      grpc.EstadisticasOuterClass.MediaReply> getCalcularMediaMethod() {
    io.grpc.MethodDescriptor<grpc.EstadisticasOuterClass.DatoRequest, grpc.EstadisticasOuterClass.MediaReply> getCalcularMediaMethod;
    if ((getCalcularMediaMethod = EstadisticasGrpc.getCalcularMediaMethod) == null) {
      synchronized (EstadisticasGrpc.class) {
        if ((getCalcularMediaMethod = EstadisticasGrpc.getCalcularMediaMethod) == null) {
          EstadisticasGrpc.getCalcularMediaMethod = getCalcularMediaMethod =
              io.grpc.MethodDescriptor.<grpc.EstadisticasOuterClass.DatoRequest, grpc.EstadisticasOuterClass.MediaReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "calcularMedia"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.EstadisticasOuterClass.DatoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.EstadisticasOuterClass.MediaReply.getDefaultInstance()))
              .setSchemaDescriptor(new EstadisticasMethodDescriptorSupplier("calcularMedia"))
              .build();
        }
      }
    }
    return getCalcularMediaMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EstadisticasStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EstadisticasStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EstadisticasStub>() {
        @java.lang.Override
        public EstadisticasStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EstadisticasStub(channel, callOptions);
        }
      };
    return EstadisticasStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EstadisticasBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EstadisticasBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EstadisticasBlockingStub>() {
        @java.lang.Override
        public EstadisticasBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EstadisticasBlockingStub(channel, callOptions);
        }
      };
    return EstadisticasBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EstadisticasFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EstadisticasFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EstadisticasFutureStub>() {
        @java.lang.Override
        public EstadisticasFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EstadisticasFutureStub(channel, callOptions);
        }
      };
    return EstadisticasFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Client Streaming: El cliente envía un flujo, el servidor responde una vez
     * </pre>
     */
    default io.grpc.stub.StreamObserver<grpc.EstadisticasOuterClass.DatoRequest> calcularMedia(
        io.grpc.stub.StreamObserver<grpc.EstadisticasOuterClass.MediaReply> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getCalcularMediaMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Estadisticas.
   */
  public static abstract class EstadisticasImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return EstadisticasGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Estadisticas.
   */
  public static final class EstadisticasStub
      extends io.grpc.stub.AbstractAsyncStub<EstadisticasStub> {
    private EstadisticasStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EstadisticasStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EstadisticasStub(channel, callOptions);
    }

    /**
     * <pre>
     * Client Streaming: El cliente envía un flujo, el servidor responde una vez
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.EstadisticasOuterClass.DatoRequest> calcularMedia(
        io.grpc.stub.StreamObserver<grpc.EstadisticasOuterClass.MediaReply> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getCalcularMediaMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Estadisticas.
   */
  public static final class EstadisticasBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<EstadisticasBlockingStub> {
    private EstadisticasBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EstadisticasBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EstadisticasBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Estadisticas.
   */
  public static final class EstadisticasFutureStub
      extends io.grpc.stub.AbstractFutureStub<EstadisticasFutureStub> {
    private EstadisticasFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EstadisticasFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EstadisticasFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_CALCULAR_MEDIA = 0;

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
        case METHODID_CALCULAR_MEDIA:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.calcularMedia(
              (io.grpc.stub.StreamObserver<grpc.EstadisticasOuterClass.MediaReply>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCalcularMediaMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              grpc.EstadisticasOuterClass.DatoRequest,
              grpc.EstadisticasOuterClass.MediaReply>(
                service, METHODID_CALCULAR_MEDIA)))
        .build();
  }

  private static abstract class EstadisticasBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EstadisticasBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.EstadisticasOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Estadisticas");
    }
  }

  private static final class EstadisticasFileDescriptorSupplier
      extends EstadisticasBaseDescriptorSupplier {
    EstadisticasFileDescriptorSupplier() {}
  }

  private static final class EstadisticasMethodDescriptorSupplier
      extends EstadisticasBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EstadisticasMethodDescriptorSupplier(String methodName) {
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
      synchronized (EstadisticasGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EstadisticasFileDescriptorSupplier())
              .addMethod(getCalcularMediaMethod())
              .build();
        }
      }
    }
    return result;
  }
}
