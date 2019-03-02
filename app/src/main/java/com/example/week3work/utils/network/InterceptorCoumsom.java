package com.example.week3work.utils.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class InterceptorCoumsom implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request=chain.request();
        Request.Builder builder=request.newBuilder()
                .addHeader("userId","105")
                .addHeader("sessionId","1551496093796105");
        Request request1=builder.build();

        return chain.proceed(request1);
    }
}
