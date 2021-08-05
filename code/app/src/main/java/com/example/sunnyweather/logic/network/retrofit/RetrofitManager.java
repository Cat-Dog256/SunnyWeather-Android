package com.example.sunnyweather.logic.network.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//https://www.jianshu.com/p/0c055ad46b6c
public class RetrofitManager {
        private static String serverBaseUrl = Constants.SERVER_URL;

        private static int connectTimeOut = 60;

        private static ConcurrentHashMap<String, Retrofit> concurrentHashMap;

        private static OkHttpClient.Builder builder;

        private static Gson dateFormat;

        static {
            concurrentHashMap = new ConcurrentHashMap<>();

            builder = new OkHttpClient.Builder();

            builder.connectTimeout(connectTimeOut, TimeUnit.SECONDS);

            dateFormat = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create();
        }
    public static void init(String baseUrl) {
        serverBaseUrl = baseUrl;
    }

    public static String getsBaseUrl() {
        return serverBaseUrl;
    }

    public static void setConnectTimeout(int sConnectTimeout) {
        connectTimeOut = sConnectTimeout;
    }

        public static Retrofit get(){
            Retrofit retrofit = concurrentHashMap.get(serverBaseUrl);
            if (retrofit == null){
                throw new  RuntimeException("baseUrl is Null");
            }
            return retrofit;
        }

        public static Retrofit get(String baseUrl){
            Retrofit retrofit = concurrentHashMap.get(baseUrl);
            if (retrofit == null){
                retrofit = createRetrofit(baseUrl);
                concurrentHashMap.put(baseUrl,retrofit);
            }
            return retrofit;
        }

        private static Retrofit createRetrofit(String baseUrl){
            return new Retrofit.Builder().baseUrl(baseUrl)
                    .client(builder.build())
//                    .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create(dateFormat))
                    .build();
        }
}
