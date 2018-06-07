package aitest.etaishuo.com.myapplication.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by fullcircle on 2016/12/31.
 */

public class ToastUtils {

    private static Toast toast;
    public static void showToast(Context context,String msg){

        if(toast==null){
            //如果toast第一次创建 makeText创建toast对象
            toast = Toast.makeText(context.getApplicationContext(),msg,Toast.LENGTH_SHORT);
        }else{
            //如果toast存在 只需要修改文字
            toast.setText(msg);
        }
        toast.show();
    }
    public static void showToast(Context context){

        if(toast==null){
            //如果toast第一次创建 makeText创建toast对象
            toast = Toast.makeText(context.getApplicationContext(),"网络连接异常 请检查网络 ",Toast.LENGTH_SHORT);
        }else{
            //如果toast存在 只需要修改文字
            toast.setText("网络连接异常 请检查网络 ");
        }
        toast.show();
    }


}
