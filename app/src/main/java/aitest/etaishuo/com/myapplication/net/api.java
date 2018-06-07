package aitest.etaishuo.com.myapplication.net;

import java.util.Map;

import aitest.etaishuo.com.myapplication.bean.CidsEntity;
import aitest.etaishuo.com.myapplication.bean.ClassBean;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



public interface api {


    @FormUrlEncoded
    @POST("weixiao/api.php")
    Call<CidsEntity> getSidList(@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("weixiao/api.php")
    Call<ClassBean> getStudentsList(@FieldMap Map<String, String> params);



    @FormUrlEncoded
    @POST("weixiao/api.php")
    Call<ClassBean> getClassList(@FieldMap Map<String, String> params);









}
