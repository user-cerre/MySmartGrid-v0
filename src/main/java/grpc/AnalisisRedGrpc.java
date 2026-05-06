package grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: direccionPicos.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AnalisisRedGrpc {

  private AnalisisRedGrpc() {}

  public static final String SERVICE_NAME = "AnalisisRed";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.DireccionPicos.PicoRequest,
      grpc.DireccionPicos.PicoReply> getDetectarPicosMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "detectarPicos",
      requestType = grpc.DireccionPicos.PicoRequest.class,
      responseType = grpc.DireccionPicos.PicoReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.DireccionPicos.PicoRequest,
      grpc.DireccionPicos.PicoReply> getDetectarPicosMethod() {
    io.grpc.MethodDescriptor<grpc.DireccionPicos.PicoRequest, grpc.DireccionPicos.PicoReply> getDetectarPicosMethod;
    if ((getDetectarPicosMethod = AnalisisRedGrpc.getDetectarPicosMethod) == null) {
      synchronized (AnalisisRedGrpc.class) {
        if ((getDetectarPicosMethod = AnalisisRedGrpc.getDetectarPicosMethod) == null) {
          AnalisisRedGrpc.getDetectarPicosMethod = getDetectarPicosMethod =
              io.grpc.MethodDescriptor.<grpc.DireccionPicos.PicoRequest, grpc.DireccionPicos.PicoReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "detectarPicos"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.DireccionPicos.PicoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.DireccionPicos.PicoReply.getDefaultInstance()))
              .setSchemaDescriptor(new AnalisisRedMethodDescriptorSupplier("detectarPicos"))
              .build();
        }
      }
    }
    return getDetectarPicosMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AnalisisRedStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AnalisisRedStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AnalisisRedStub>() {
        @java.lang.Override
        public AnalisisRedStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AnalisisRedStub(channel, callOptions);
        }
      };
    return AnalisisRedStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AnalisisRedBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AnalisisRedBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AnalisisRedBlockingStub>() {
        @java.lang.Override
        public AnalisisRedBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AnalisisRedBlockingStub(channel, callOptions);
        }
      };
    return AnalisisRedBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AnalisisRedFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AnalisisRedFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AnalisisRedFutureStub>() {
        @java.lang.Override
        public AnalisisRedFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AnalisisRedFutureStub(channel, callOptions);
        }
      };
    return AnalisisRedFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<grpc.DireccionPicos.PicoRequest> detectarPicos(
        io.grpc.stub.StreamObserver<grpc.DireccionPicos.PicoReply> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getDetectarPicosMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AnalisisRed.
   */
  public static abstract class AnalisisRedImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AnalisisRedGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AnalisisRed.
   */
  public static final class AnalisisRedStub
      extends io.grpc.stub.AbstractAsyncStub<AnalisisRedStub> {
    private AnalisisRedStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AnalisisRedStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AnalisisRedStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.DireccionPicos.PicoRequest> detectarPicos(
        io.grpc.stub.StreamObserver<grpc.DireccionPicos.PicoReply> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getDetectarPicosMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AnalisisRed.
   */
  public static final class AnalisisRedBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AnalisisRedBlockingStub> {
    private AnalisisRedBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AnalisisRedBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AnalisisRedBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AnalisisRed.
   */
  public static final class AnalisisRedFutureStub
      extends io.grpc.stub.AbstractFutureStub<AnalisisRedFutureStub> {
    private AnalisisRedFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AnalisisRedFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AnalisisRedFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_DETECTAR_PICOS = 0;

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
        case METHODID_DETECTAR_PICOS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.detectarPicos(
              (io.grpc.stub.StreamObserver<grpc.DireccionPicos.PicoReply>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getDetectarPicosMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              grpc.DireccionPicos.PicoRequest,
              grpc.DireccionPicos.PicoReply>(
                service, METHODID_DETECTAR_PICOS)))
        .build();
  }

  private static abstract class AnalisisRedBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AnalisisRedBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.DireccionPicos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AnalisisRed");
    }
  }

  private static final class AnalisisRedFileDescriptorSupplier
      extends AnalisisRedBaseDescriptorSupplier {
    AnalisisRedFileDescriptorSupplier() {}
  }

  private static final class AnalisisRedMethodDescriptorSupplier
      extends AnalisisRedBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AnalisisRedMethodDescriptorSupplier(String methodName) {
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
      synchronized (AnalisisRedGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AnalisisRedFileDescriptorSupplier())
              .addMethod(getDetectarPicosMethod())
              .build();
        }
      }
    }
    return result;
  }
}
