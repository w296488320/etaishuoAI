package aitest.etaishuo.com.myapplication;

import android.app.Application;
import android.content.Context;

import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;


/**
 * Created by lyh on 2018/5/22.
 */

public class App extends Application {

    private static EventManager mAsr;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        initBaidu();
        context=getApplicationContext();


    }

    private void initBaidu() {

        // this是Activity或其它Context类
        mAsr = EventManagerFactory.create(this, "asr");

    }


    public static EventManager getAsr(){
        return mAsr;
    }

}
