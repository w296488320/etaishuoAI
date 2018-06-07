package aitest.etaishuo.com.myapplication.utils;

import com.google.gson.Gson;

/**
 * Created by lyh on 2018/4/9.
 */

public class GsonUtils {

    static Gson mGson;

      public static Gson getGson(){
          if(mGson==null){
              mGson=new Gson();
          }
          return  mGson;
      }

}
