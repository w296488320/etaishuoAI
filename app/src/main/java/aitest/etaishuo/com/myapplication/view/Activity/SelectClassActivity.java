package aitest.etaishuo.com.myapplication.view.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.security.Key;
import java.util.ArrayList;

import aitest.etaishuo.com.myapplication.MainActivity;
import aitest.etaishuo.com.myapplication.R;
import aitest.etaishuo.com.myapplication.base.BaseActivity;

import aitest.etaishuo.com.myapplication.bean.CidsEntity;
import aitest.etaishuo.com.myapplication.bean.CidsItemEntity;
import aitest.etaishuo.com.myapplication.config.MainConfig;
import aitest.etaishuo.com.myapplication.net.RequestBean;
import aitest.etaishuo.com.myapplication.net.net;
import aitest.etaishuo.com.myapplication.utils.LogUtils;
import aitest.etaishuo.com.myapplication.utils.SpUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class SelectClassActivity extends BaseActivity {

    private ListView mLv_selectclass;
    private Button mBt_back;
    private MyHandler mMHandler;
    private MyAdapter mAdapter;
     ArrayList<CidsItemEntity> cidList=new ArrayList<CidsItemEntity>();


    String Selcetcid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectclass);
        //showLoadingDialog();
        initView();
        initData();
        dismiss();
    }








    private void initData() {

        String sid = getIntent().getExtras().getString("Sid");

        if(sid==null){
            sid = SpUtil.getString(this, MainConfig.Sid, "");
        }


        net.getApi().getSidList(RequestBean.getCidsUseSid(sid)) .enqueue(new Callback<CidsEntity>() {


            @Override
            public void onResponse(Call<CidsEntity> call, Response<CidsEntity> response) {
                CidsEntity body = response.body();



                Message message = new Message();
                message.what=1;
                message.obj=body;
                mMHandler.handleMessage(message);



            }

            @Override
            public void onFailure(Call<CidsEntity> call, Throwable t) {

            }
        });

    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                CidsEntity obj = (CidsEntity) msg.obj;
                ArrayList<CidsItemEntity> list = obj.message;
                cidList.clear();
                cidList.addAll(list);
                mAdapter.notifyDataSetChanged();
            }


        }



    }

    private void initView() {
        mMHandler = new MyHandler();
        mLv_selectclass = (ListView)findViewById(R.id.lv_selectclass);
        mBt_back = (Button)findViewById(R.id.bt_back);





        mBt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAdapter = new MyAdapter();
        mLv_selectclass.setAdapter(mAdapter);


//        mLv_selectclass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        mLv_selectclass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showLoadingDialog();
                Selcetcid= cidList.get(position).cid;
                startMainActivity();

            }
        });

    }

    private void startMainActivity() {


      Intent intent =  new Intent(this,MainActivity.class);

      intent.putExtra("Cid",Selcetcid);

      startActivity(intent);


    }


    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return (cidList==null)?0:cidList.size();
        }

        @Override
        public CidsItemEntity getItem(int position) {
            return cidList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            MyViewHolder holder;
            if(convertView==null){
                holder=new MyViewHolder();
                convertView= LayoutInflater.from(SelectClassActivity.this).inflate(R.layout.activity_selectclass__item, null);

                holder.cid=(TextView) convertView.findViewById(R.id.tv_cid);

                holder.cidName=(TextView) convertView.findViewById(R.id.tv_cidname);
                convertView.setTag(holder);
            }else {
                holder = (MyViewHolder) convertView.getTag();
            }


//            holder.cidName.setText(cidList.get(position).tagname);
//            holder.cid.setText(cidList.get(position).cid);
            CidsItemEntity cidsItemEntity = cidList.get(position);
            holder.cidName.setText(cidsItemEntity.tagname);
            holder.cid.setText(cidsItemEntity.cid);

            return convertView;
        }

        class MyViewHolder  {
            TextView cid;
            TextView cidName;
        }
    }
}
