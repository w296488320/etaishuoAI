package aitest.etaishuo.com.myapplication.net;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import aitest.etaishuo.com.myapplication.bean.PingLunBean;
import aitest.etaishuo.com.myapplication.bean.request.AIbean;
import aitest.etaishuo.com.myapplication.utils.AppUtils;


/**
 * Created by lyh on 2018/5/3.
 */

public class RequestBean {



    /**
     * 根据SID获取所有班级
     * GetClass
     * @author vincent
     * @date
     * @args
     * @param =4 ac=EleClassCard op=getClass sid=xxx
     */
    public static Map<String, String> getCidsUseSid(String sid) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("ac", "EleClassCard");
        params.put("v", "4");
        params.put("op", "getClass");
        params.put("app", AppUtils.getVersionName());
        params.put("sid",sid);
        return params;
    }




    /**
     * AllStudent
     * @author vincent
     * @date
     * @args
     * @param =3 ac=Checkin op=AllStudent app= sid= cid=
     */
    public static Map<String, String>  getClassAllStudentData(String sid,String cid) {
        HashMap<String, String> params = new HashMap<>();
        params.put("ac", "Checkin");
        params.put("v", "3");
        params.put("op", "AllStudent");
        params.put("app", AppUtils.getVersionName());
        params.put("sid", sid);
        params.put("cid", cid);

        return params;
    }





    public static AIbean AI(String text){
        AIbean aIbean = new AIbean();
        aIbean.text=text;
        return aIbean;
    }


    public static PingLunBean pinglun(String text){
        PingLunBean aIbean = new PingLunBean();
        //aIbean.access_token=token;
        aIbean.text=text;
        aIbean.type=7;
        // aIbean.charset="UTF-8";
        return aIbean;
    }





    public static HashMap<String,String> getToken(String client_id,String client_secret){
        HashMap<String, String> bean = new HashMap<>();
        bean.put("grant_type","client_credentials");

        bean.put("client_id",client_id);
        bean.put("client_secret",client_secret);
        return  bean;
    }










}
