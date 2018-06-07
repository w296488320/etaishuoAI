package aitest.etaishuo.com.myapplication.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;


import com.wang.avi.AVLoadingIndicatorView;

import aitest.etaishuo.com.myapplication.R;

public class BaseActivity extends AppCompatActivity {
    private AVLoadingIndicatorView mLoading;
    public Dialog loadDialog;
    private View mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void showLoadingDialog() {

        if(mLoading==null){
            mView = LayoutInflater.from(this).inflate(R.layout.layout_loading, null);


            mLoading = (AVLoadingIndicatorView)mView.findViewById(R.id.av_loading);

            mLoading.show();


        }else {
            mLoading.show();
        }

    }




    public void dismiss(){

        if(loadDialog!=null){
            mLoading.hide();
        }

    }


}
