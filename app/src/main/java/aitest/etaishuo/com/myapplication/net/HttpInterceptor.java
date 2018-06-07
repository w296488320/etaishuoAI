package aitest.etaishuo.com.myapplication.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class HttpInterceptor implements Interceptor {
    private String value="application/json";

    public HttpInterceptor(String value){
        this.value=value;
    }


    public HttpInterceptor(){

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        Request requst = builder.addHeader("Content-type",value).build();
        return chain.proceed(requst);
    }





}
