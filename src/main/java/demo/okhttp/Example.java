package demo.okhttp;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.Duration;

public class Example {
    public static void main(String[] args) {
        String url = "https://publicobject.com/helloworld.txt";

        //1.通过Builder模式得到OkHttpClient
        //OkHttpClient 包含了对网络请求的全局配置信息，包括 链接超时时间、读写超时时间、链接失败重试 、拦截器等各种配置
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(Duration.ofSeconds(10))
                .readTimeout(Duration.ofSeconds(10))
                .writeTimeout(Duration.ofSeconds(10))
                .retryOnConnectionFailure(true)
                .build();
        //2.通过Builder模式得到Request
        /**
         * Request 包含了网络请求时的所有请求参数，一共包含以下五个：
         *
         * url。即本次的网络请求地址以及可能包含的 query 键值对
         * method。即请求方式，可选参数有 GET、HEAD、POST、DELETE、PUT、PATCH
         * headers。即请求头，可用来存 token、时间戳等
         * body。即请求体
         * tags。可用来唯一标识本次请求
         */
        Request request = new Request.Builder().url(url).build();

        //3.通过newCall得到Call对象
        //Call 就用于发起请求，可用于执行 同步请求（execute）、异步请求（enqueue）、取消请求（cancel） 等各种操作
        //Call 是一个接口，我们可以将其看做是网络请求的启动器，可用于发起同步请求或异步请求，但重复发起多次请求的话会抛出异常
        //返回的是一个realCall
        Call call = okHttpClient.newCall(request);

        try {
            //4.调用 execute 方法发起同步请求并返回一个 Response 对象，
            //Response 就包含了此次网络请求的所有返回信息，如果请求失败的话此方法会抛出异常
            /**
             * 通过getResponseWithInterceptorChain()获得Response对象
             * 方法里还用到了dispatcher
             */
            Response response = call.execute();
            /**
             *ealCall 的 enqueue方法会将外部传入的 Callback 包装为一个 AsyncCall 对象后传给 dispatcher
             */
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                }
            });
            //5.拿到 Response 对象的 body 并以字符串流的方式进行读取
            String string = response.body().string();
            System.out.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
