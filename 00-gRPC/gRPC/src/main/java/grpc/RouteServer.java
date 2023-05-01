package grpc;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class RouteServer {
    private final int port;
    private final Server server;

    public RouteServer(int port) throws IOException{
        this(port, Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create()));
    }

    public RouteServer(int port, ServerBuilder<?> serverBuilder) throws IOException {
        this.port = port;
        this.server = serverBuilder.addService(new RouteService()).build();
    }

    public void start() throws IOException{
        server.start();
    }

    private void blockUntilShutdown() throws InterruptedException {
        if(server != null){
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception{
        RouteServer server = new RouteServer(8980);
        server.start();
        server.blockUntilShutdown();
    }

    private static class RouteService extends RouteGrpc.RouteImplBase{
        private static final String DATE_FORMAT = "MM/dd/yyyy - HH:mm:ss z";

        @Override
        public void getTimeInTimezone(RouteOuterClass.Timezone request, StreamObserver<RouteOuterClass.Time> responseObserver) {
            responseObserver.onNext(calculateTime(request));
            responseObserver.onCompleted();
        }

        private RouteOuterClass.Time calculateTime(RouteOuterClass.Timezone zone){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
            ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of(zone.getTimezone()));
            return RouteOuterClass.Time.newBuilder().setTime(zdt.format(dtf)).build();
        }
    }
}