package aitest.etaishuo.com.myapplication.net;


import java.util.Map;

import aitest.etaishuo.com.myapplication.bean.FenxiResut;

import aitest.etaishuo.com.myapplication.bean.PingLunBean;
import aitest.etaishuo.com.myapplication.bean.PingLunResut_bean;
import aitest.etaishuo.com.myapplication.bean.QgResut;
import aitest.etaishuo.com.myapplication.bean.request.AIbean;
import aitest.etaishuo.com.myapplication.bean.request.TokenResutBean;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by lyh on 2018/4/2.
 */

public interface AIapi {


    //情感倾向分析
   // @Multipart
    //@FormUrlEncoded
    @Headers({"Content-type:application/json"})
    @POST("rpc/2.0/nlp/v1/sentiment_classify")
    Call<QgResut> QgSend(@Body AIbean bean,
                         @Query("access_token") String token,
                         @Query("charset") String utf
    );




    //path在？之前  query在？之后
    //field是 post body字段

    //语法分析
   // @Multipart
    //@FormUrlEncoded
    @Headers({"Content-type:application/json"})
    @POST("rpc/2.0/nlp/v1/lexer")
    Call<FenxiResut> CySend(@Body AIbean bean,
                            @Query("access_token") String token,
                            @Query("charset") String utf
    );


    //获取Token
    //语法分析
    @FormUrlEncoded
    @POST("oauth/2.0/token")
    Call<TokenResutBean> getToken(@FieldMap Map<String,String> bean);




    //获取Token
    //语法分析
    @Headers({"Content-type:application/json"})
    @POST("rpc/2.0/nlp/v2/comment_tag")
    Call<PingLunResut_bean> PingLun(@Body PingLunBean bean,
                                    @Query("access_token") String token,
                                    @Query("charset") String utf);








}
