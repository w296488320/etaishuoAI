package aitest.etaishuo.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.Key;

import aitest.etaishuo.com.myapplication.base.BaseActivity;
import aitest.etaishuo.com.myapplication.bean.CidsEntity;
import aitest.etaishuo.com.myapplication.config.MainConfig;
import aitest.etaishuo.com.myapplication.net.RequestBean;
import aitest.etaishuo.com.myapplication.net.net;
import aitest.etaishuo.com.myapplication.utils.SpUtil;
import aitest.etaishuo.com.myapplication.utils.ThreadUtils;
import aitest.etaishuo.com.myapplication.utils.ToastUtils;
import aitest.etaishuo.com.myapplication.view.Activity.SelectClassActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SelectActivity  extends BaseActivity{

    private EditText mSid;
    String sid="";
    private Button mBtn_sure;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bing);

        initView();



    }







    private void initView() {

        mSid = (EditText) findViewById(R.id.tv_sid);

        mBtn_sure = (Button) findViewById(R.id.btn_sure);




//        mSid.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//                sid = mSid.getText().toString();
//                if(sid.length()>=4){
////                    showLoadingDialog();
//
//                    getSids(sid);
//                }
//            }
//        });




        mBtn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ToastUtils.showToast(SelectActivity.this,"66666666666666");


                sid=mSid.getText().toString().trim();
                if(sid==null||sid.equals("")||sid.length()<4){
                    ToastUtils.showToast(App.context,"sid不正确");
                }
                else {
                    showLoadingDialog();
                    getSids(sid);

                    dismiss();
                    finish();

                    //SpUtil.putString(BingActivity.this, Key.Title,mTagname);
                    //SpUtil.putString(BingActivity.this, Key.teacher,mTeacherJson);

                }
            }
        });

    }

    private void startSelectClass(String sid) {
      Intent intent =  new Intent(this, SelectClassActivity.class);
      intent.putExtra("Sid",sid);
      startActivity(intent);

    }

    private void getSids(String sid) {
        net.getApi().getSidList(RequestBean.getCidsUseSid(sid)).enqueue(new Callback<CidsEntity>() {
            @Override
            public void onResponse(Call<CidsEntity> call, Response<CidsEntity> response) {
                CidsEntity body = response.body();
                if(body!=null){
                    if(!body.result){
                        ToastUtils.showToast(App.context,"您输入的sid不正确");
                        dismiss();
                    }else {

                        ThreadUtils.runOnMainThread(new Runnable() {
                            @Override
                            public void run() {


                                SpUtil.putString(App.context, MainConfig.Sid,sid);

                                startSelectClass(sid);


                            }
                        });





                    }
                }
            }

            @Override
            public void onFailure(Call<CidsEntity> call, Throwable t) {

            }
        });

    }




}
