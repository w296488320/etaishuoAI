package aitest.etaishuo.com.myapplication.net;

import android.util.Log;

import java.io.File;
import java.util.concurrent.TimeUnit;

import aitest.etaishuo.com.myapplication.App;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lyh on 2018/5/2.
 */

public class AInet {


    private static Retrofit sRetrofit1;
    private static AIapi sMAIapi;

    private static HttpLoggingInterceptor sLog;

    public static AIapi getApi(){
        //设置网络请求的Url地址
        //设置数据解析器
        if(sRetrofit1==null){






            //打印retrofit日志
            sLog = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    //打印retrofit日志
                    Log.e("请求地址","retrofitBack = "+message);
                }
            });
            sLog.setLevel(HttpLoggingInterceptor.Level.BODY);





            sRetrofit1 = new Retrofit.Builder()
                    .baseUrl("https://aip.baidubce.com/") //设置网络请求的Url地址
                    .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                    .client(getOkHttpClient())
                    .build();
            sMAIapi = sRetrofit1.create(AIapi.class);
            return sMAIapi;
        }
          return sMAIapi;

    }


    private static OkHttpClient getOkHttpClient() {
        Cache cache = new Cache(new File(App.context.getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);


        OkHttpClient.Builder builder = new OkHttpClient.Builder().
                cache(cache).
                addInterceptor(sLog).
                connectTimeout(6, TimeUnit.SECONDS)
                .connectTimeout(6 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(6 * 1000, TimeUnit.MILLISECONDS)
                //.proxy(Proxy.NO_PROXY) //防代理 防抓包
                .retryOnConnectionFailure(true)
                .addInterceptor(new HttpInterceptor());//添加 json类型 拦截器 发送的数据是 json类型

        return builder.build();
    }










}