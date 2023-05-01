package grpc;

import io.grpc.Channel;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;

public class RouteClient {
    private final RouteGrpc.RouteBlockingStub blockingStub;

    public RouteClient(Channel channel){
        this.blockingStub = RouteGrpc.newBlockingStub(channel);
    }

    public static void main(String[] args){
        RouteOuterClass.Timezone request;
        RouteOuterClass.Time response;

        ManagedChannel channel = Grpc.newChannelBuilder("localhost:8980", InsecureChannelCredentials.create()).build();
        RouteClient client = new RouteClient(channel);

        request = RouteOuterClass.Timezone.newBuilder().setTimezone("Asia/Kolkata").build();
        response = client.blockingStub.getTimeInTimezone(request);
        System.out.println(response.getTime());

        request = RouteOuterClass.Timezone.newBuilder().setTimezone("UTC").build();
        response = client.blockingStub.getTimeInTimezone(request);
        System.out.println(response.getTime());

        request = RouteOuterClass.Timezone.newBuilder().setTimezone("Europe/Vienna").build();
        response = client.blockingStub.getTimeInTimezone(request);
        System.out.println(response.getTime());
    }
}