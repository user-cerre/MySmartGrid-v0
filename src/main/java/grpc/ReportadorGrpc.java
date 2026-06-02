package grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: Reportador.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ReportadorGrpc {

  private ReportadorGrpc() {}

  public static final String SERVICE_NAME = "Reportador";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.ReportadorOuterClass.ConsumoRequest,
      grpc.ReportadorOuterClass.ReporteReply> getReportarMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "reportar",
      requestType = grpc.ReportadorOuterClass.ConsumoRequest.class,
      responseType = grpc.ReportadorOuterClass.ReporteReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.ReportadorOuterClass.ConsumoRequest,
      grpc.ReportadorOuterClass.ReporteReply> getReportarMethod() {
    io.grpc.MethodDescriptor<grpc.ReportadorOuterClass.ConsumoRequest, grpc.ReportadorOuterClass.ReporteReply> getReportarMethod;
    if ((getReportarMethod = ReportadorGrpc.getReportarMethod) == null) {
      synchronized (ReportadorGrpc.class) {
        if ((getReportarMethod = ReportadorGrpc.getReportarMethod) == null) {
          ReportadorGrpc.getReportarMethod = getReportarMethod =
              io.grpc.MethodDescriptor.<grpc.ReportadorOuterClass.ConsumoRequest, grpc.ReportadorOuterClass.ReporteReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "reportar"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.ReportadorOuterClass.ConsumoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.ReportadorOuterClass.ReporteReply.getDefaultInstance()))
              .setSchemaDescriptor(new ReportadorMethodDescriptorSupplier("reportar"))
              .build();
        }
      }
    }
    return getReportarMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ReportadorStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReportadorStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReportadorStub>() {
        @java.lang.Override
        public ReportadorStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReportadorStub(channel, callOptions);
        }
      };
    return ReportadorStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ReportadorBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReportadorBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReportadorBlockingStub>() {
        @java.lang.Override
        public ReportadorBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReportadorBlockingStub(channel, callOptions);
        }
      };
    return ReportadorBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ReportadorFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReportadorFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReportadorFutureStub>() {
        @java.lang.Override
        public ReportadorFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReportadorFutureStub(channel, callOptions);
        }
      };
    return ReportadorFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<grpc.ReportadorOuterClass.ConsumoRequest> reportar(
        io.grpc.stub.StreamObserver<grpc.ReportadorOuterClass.ReporteReply> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getReportarMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Reportador.
   */
  public static abstract class ReportadorImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ReportadorGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Reportador.
   */
  public static final class ReportadorStub
      extends io.grpc.stub.AbstractAsyncStub<ReportadorStub> {
    private ReportadorStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReportadorStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReportadorStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.ReportadorOuterClass.ConsumoRequest> reportar(
        io.grpc.stub.StreamObserver<grpc.ReportadorOuterClass.ReporteReply> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getReportarMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Reportador.
   */
  public static final class ReportadorBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ReportadorBlockingStub> {
    private ReportadorBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReportadorBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReportadorBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Reportador.
   */
  public static final class ReportadorFutureStub
      extends io.grpc.stub.AbstractFutureStub<ReportadorFutureStub> {
    private ReportadorFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReportadorFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReportadorFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_REPORTAR = 0;

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
        case METHODID_REPORTAR:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.reportar(
              (io.grpc.stub.StreamObserver<grpc.ReportadorOuterClass.ReporteReply>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getReportarMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              grpc.ReportadorOuterClass.ConsumoRequest,
              grpc.ReportadorOuterClass.ReporteReply>(
                service, METHODID_REPORTAR)))
        .build();
  }

  private static abstract class ReportadorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ReportadorBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.ReportadorOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Reportador");
    }
  }

  private static final class ReportadorFileDescriptorSupplier
      extends ReportadorBaseDescriptorSupplier {
    ReportadorFileDescriptorSupplier() {}
  }

  private static final class ReportadorMethodDescriptorSupplier
      extends ReportadorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ReportadorMethodDescriptorSupplier(String methodName) {
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
      synchronized (ReportadorGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ReportadorFileDescriptorSupplier())
              .addMethod(getReportarMethod())
              .build();
        }
      }
    }
    return result;
  }
}
