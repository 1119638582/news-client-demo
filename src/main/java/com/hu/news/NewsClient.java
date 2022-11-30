package com.hu.news;

import com.hu.news.proto.NewsProto;
import com.hu.news.proto.NewsServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;

public class NewsClient {
    private static final String host = "localhost";
    private static final int port = 9999;

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host,port).usePlaintext()
                //无需加密或认证
                .build();

        try {
            NewsServiceGrpc.NewsServiceBlockingStub blockingStub = NewsServiceGrpc.newBlockingStub(channel);
            NewsProto.NewsRequest request = NewsProto.NewsRequest.newBuilder().setData("20221130").build();
            NewsProto.NewsRespose response = blockingStub.list(request);
            List<NewsProto.News> newsList = response.getNewsList();
            for (NewsProto.News news:newsList) {
                System.out.println(news.getTotal() + " : " + news.getContent());
            }
        }
        finally {
            channel.shutdown();
        }
    }
}
