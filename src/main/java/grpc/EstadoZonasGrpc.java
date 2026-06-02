package grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: examen.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class EstadoZonasGrpc {

  private EstadoZonasGrpc() {}

  public static final String SERVICE_NAME = "EstadoZonas";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.EstadoZonasProto.EstadoZonasRequest,
      grpc.EstadoZonasProto.ZonaSaturadaReply> getZonasSaturadasMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "zonasSaturadas",
      requestType = grpc.EstadoZonasProto.EstadoZonasRequest.class,
      responseType = grpc.EstadoZonasProto.ZonaSaturadaReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.EstadoZonasProto.EstadoZonasRequest,
      grpc.EstadoZonasProto.ZonaSaturadaReply> getZonasSaturadasMethod() {
    io.grpc.MethodDescriptor<grpc.EstadoZonasProto.EstadoZonasRequest, grpc.EstadoZonasProto.ZonaSaturadaReply> getZonasSaturadasMethod;
    if ((getZonasSaturadasMethod = EstadoZonasGrpc.getZonasSaturadasMethod) == null) {
      synchronized (EstadoZonasGrpc.class) {
        if ((getZonasSaturadasMethod = EstadoZonasGrpc.getZonasSaturadasMethod) == null) {
          EstadoZonasGrpc.getZonasSaturadasMethod = getZonasSaturadasMethod =
              io.grpc.MethodDescriptor.<grpc.EstadoZonasProto.EstadoZonasRequest, grpc.EstadoZonasProto.ZonaSaturadaReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "zonasSaturadas"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.EstadoZonasProto.EstadoZonasRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.EstadoZonasProto.ZonaSaturadaReply.getDefaultInstance()))
              .setSchemaDescriptor(new EstadoZonasMethodDescriptorSupplier("zonasSaturadas"))
              .build();
        }
      }
    }
    return getZonasSaturadasMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EstadoZonasStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EstadoZonasStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EstadoZonasStub>() {
        @java.lang.Override
        public EstadoZonasStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EstadoZonasStub(channel, callOptions);
        }
      };
    return EstadoZonasStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EstadoZonasBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EstadoZonasBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EstadoZonasBlockingStub>() {
        @java.lang.Override
        public EstadoZonasBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EstadoZonasBlockingStub(channel, callOptions);
        }
      };
    return EstadoZonasBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EstadoZonasFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EstadoZonasFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EstadoZonasFutureStub>() {
        @java.lang.Override
        public EstadoZonasFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EstadoZonasFutureStub(channel, callOptions);
        }
      };
    return EstadoZonasFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void zonasSaturadas(grpc.EstadoZonasProto.EstadoZonasRequest request,
        io.grpc.stub.StreamObserver<grpc.EstadoZonasProto.ZonaSaturadaReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getZonasSaturadasMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service EstadoZonas.
   */
  public static abstract class EstadoZonasImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return EstadoZonasGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service EstadoZonas.
   */
  public static final class EstadoZonasStub
      extends io.grpc.stub.AbstractAsyncStub<EstadoZonasStub> {
    private EstadoZonasStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EstadoZonasStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EstadoZonasStub(channel, callOptions);
    }

    /**
     */
    public void zonasSaturadas(grpc.EstadoZonasProto.EstadoZonasRequest request,
        io.grpc.stub.StreamObserver<grpc.EstadoZonasProto.ZonaSaturadaReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getZonasSaturadasMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service EstadoZonas.
   */
  public static final class EstadoZonasBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<EstadoZonasBlockingStub> {
    private EstadoZonasBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EstadoZonasBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EstadoZonasBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<grpc.EstadoZonasProto.ZonaSaturadaReply> zonasSaturadas(
        grpc.EstadoZonasProto.EstadoZonasRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getZonasSaturadasMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service EstadoZonas.
   */
  public static final class EstadoZonasFutureStub
      extends io.grpc.stub.AbstractFutureStub<EstadoZonasFutureStub> {
    private EstadoZonasFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EstadoZonasFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EstadoZonasFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_ZONAS_SATURADAS = 0;

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
        case METHODID_ZONAS_SATURADAS:
          serviceImpl.zonasSaturadas((grpc.EstadoZonasProto.EstadoZonasRequest) request,
              (io.grpc.stub.StreamObserver<grpc.EstadoZonasProto.ZonaSaturadaReply>) responseObserver);
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
          getZonasSaturadasMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              grpc.EstadoZonasProto.EstadoZonasRequest,
              grpc.EstadoZonasProto.ZonaSaturadaReply>(
                service, METHODID_ZONAS_SATURADAS)))
        .build();
  }

  private static abstract class EstadoZonasBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EstadoZonasBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.EstadoZonasProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EstadoZonas");
    }
  }

  private static final class EstadoZonasFileDescriptorSupplier
      extends EstadoZonasBaseDescriptorSupplier {
    EstadoZonasFileDescriptorSupplier() {}
  }

  private static final class EstadoZonasMethodDescriptorSupplier
      extends EstadoZonasBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EstadoZonasMethodDescriptorSupplier(String methodName) {
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
      synchronized (EstadoZonasGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EstadoZonasFileDescriptorSupplier())
              .addMethod(getZonasSaturadasMethod())
              .build();
        }
      }
    }
    return result;
  }
}
