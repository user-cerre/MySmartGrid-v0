package grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: Procesador.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ProcesadorGrpc {

  private ProcesadorGrpc() {}

  public static final String SERVICE_NAME = "Procesador";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.ProcesadorOuterClass.ProcesarRequest,
      grpc.ProcesadorOuterClass.ProcesarReply> getProcesarMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "procesar",
      requestType = grpc.ProcesadorOuterClass.ProcesarRequest.class,
      responseType = grpc.ProcesadorOuterClass.ProcesarReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.ProcesadorOuterClass.ProcesarRequest,
      grpc.ProcesadorOuterClass.ProcesarReply> getProcesarMethod() {
    io.grpc.MethodDescriptor<grpc.ProcesadorOuterClass.ProcesarRequest, grpc.ProcesadorOuterClass.ProcesarReply> getProcesarMethod;
    if ((getProcesarMethod = ProcesadorGrpc.getProcesarMethod) == null) {
      synchronized (ProcesadorGrpc.class) {
        if ((getProcesarMethod = ProcesadorGrpc.getProcesarMethod) == null) {
          ProcesadorGrpc.getProcesarMethod = getProcesarMethod =
              io.grpc.MethodDescriptor.<grpc.ProcesadorOuterClass.ProcesarRequest, grpc.ProcesadorOuterClass.ProcesarReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "procesar"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.ProcesadorOuterClass.ProcesarRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.ProcesadorOuterClass.ProcesarReply.getDefaultInstance()))
              .setSchemaDescriptor(new ProcesadorMethodDescriptorSupplier("procesar"))
              .build();
        }
      }
    }
    return getProcesarMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProcesadorStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProcesadorStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProcesadorStub>() {
        @java.lang.Override
        public ProcesadorStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProcesadorStub(channel, callOptions);
        }
      };
    return ProcesadorStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProcesadorBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProcesadorBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProcesadorBlockingStub>() {
        @java.lang.Override
        public ProcesadorBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProcesadorBlockingStub(channel, callOptions);
        }
      };
    return ProcesadorBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProcesadorFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProcesadorFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProcesadorFutureStub>() {
        @java.lang.Override
        public ProcesadorFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProcesadorFutureStub(channel, callOptions);
        }
      };
    return ProcesadorFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<grpc.ProcesadorOuterClass.ProcesarRequest> procesar(
        io.grpc.stub.StreamObserver<grpc.ProcesadorOuterClass.ProcesarReply> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getProcesarMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Procesador.
   */
  public static abstract class ProcesadorImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ProcesadorGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Procesador.
   */
  public static final class ProcesadorStub
      extends io.grpc.stub.AbstractAsyncStub<ProcesadorStub> {
    private ProcesadorStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProcesadorStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProcesadorStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.ProcesadorOuterClass.ProcesarRequest> procesar(
        io.grpc.stub.StreamObserver<grpc.ProcesadorOuterClass.ProcesarReply> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getProcesarMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Procesador.
   */
  public static final class ProcesadorBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ProcesadorBlockingStub> {
    private ProcesadorBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProcesadorBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProcesadorBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Procesador.
   */
  public static final class ProcesadorFutureStub
      extends io.grpc.stub.AbstractFutureStub<ProcesadorFutureStub> {
    private ProcesadorFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProcesadorFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProcesadorFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_PROCESAR = 0;

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
        case METHODID_PROCESAR:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.procesar(
              (io.grpc.stub.StreamObserver<grpc.ProcesadorOuterClass.ProcesarReply>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getProcesarMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              grpc.ProcesadorOuterClass.ProcesarRequest,
              grpc.ProcesadorOuterClass.ProcesarReply>(
                service, METHODID_PROCESAR)))
        .build();
  }

  private static abstract class ProcesadorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProcesadorBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.ProcesadorOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Procesador");
    }
  }

  private static final class ProcesadorFileDescriptorSupplier
      extends ProcesadorBaseDescriptorSupplier {
    ProcesadorFileDescriptorSupplier() {}
  }

  private static final class ProcesadorMethodDescriptorSupplier
      extends ProcesadorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ProcesadorMethodDescriptorSupplier(String methodName) {
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
      synchronized (ProcesadorGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProcesadorFileDescriptorSupplier())
              .addMethod(getProcesarMethod())
              .build();
        }
      }
    }
    return result;
  }
}
