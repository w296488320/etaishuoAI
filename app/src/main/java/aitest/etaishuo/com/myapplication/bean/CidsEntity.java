package aitest.etaishuo.com.myapplication.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/6.
 */
public class CidsEntity {
   public ArrayList<CidsItemEntity> message;
   public String protocol;
   public boolean result;


   @Override
   public String toString() {
      return "CidsEntity{" +
              "message=" + message +
              ", protocol='" + protocol + '\'' +
              ", result=" + result +
              '}';
   }





}
