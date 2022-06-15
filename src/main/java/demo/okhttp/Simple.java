package demo.okhttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Simple {
    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder().url("https://www.baidu.com?name=parade").get().build();
        Response execute = null;
        try {
            execute = okHttpClient.newCall(build).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = execute.request().url().toString();
        System.out.println(url);
    }
}
