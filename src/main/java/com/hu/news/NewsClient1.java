package com.hu.news;

import com.hu.news.proto.NewsProto;
import com.hu.news.proto.NewsServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;

public class NewsClient1 {
    private static final String host = "localhost";
    private static final int port = 9999;

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host,port).usePlaintext()
                //无需加密或认证
                .build();

        try {
            //同步阻塞
            NewsServiceGrpc.NewsServiceBlockingStub blockingStub = NewsServiceGrpc.newBlockingStub(channel);
            //传入参数，构造请求
            NewsProto.StringRequest request = NewsProto.StringRequest.newBuilder().setName("huninghao").build();
            //调用方法  获得响应
            NewsProto.StringResponse response = blockingStub.hello(request);
            System.out.println(response.getResult());

        }
        finally {
            channel.shutdown();
        }
    }
}
