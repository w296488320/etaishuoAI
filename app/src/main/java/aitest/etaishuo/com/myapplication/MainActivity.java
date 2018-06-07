package aitest.etaishuo.com.myapplication;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.speech.EventListener;
import com.baidu.speech.asr.SpeechConstant;
import com.baidu.speech.utils.LogUtil;
import com.github.stuxuhai.jpinyin.PinyinException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import aitest.etaishuo.com.myapplication.base.BaseActivity;
import aitest.etaishuo.com.myapplication.bean.BiJiaoBean;
import aitest.etaishuo.com.myapplication.bean.ClassBean;
import aitest.etaishuo.com.myapplication.bean.FenxiResut;
import aitest.etaishuo.com.myapplication.bean.Fenxi_item_bean;
import aitest.etaishuo.com.myapplication.bean.PingLunResut_bean;
import aitest.etaishuo.com.myapplication.bean.PingLun_item;
import aitest.etaishuo.com.myapplication.bean.QgResut;
import aitest.etaishuo.com.myapplication.bean.Score;
import aitest.etaishuo.com.myapplication.bean.StudentBean;
import aitest.etaishuo.com.myapplication.bean.request.TokenResutBean;
import aitest.etaishuo.com.myapplication.bean.request.item_bean;
import aitest.etaishuo.com.myapplication.config.MainConfig;
import aitest.etaishuo.com.myapplication.net.RequestBean;
import aitest.etaishuo.com.myapplication.net.AInet;
import aitest.etaishuo.com.myapplication.net.net;
import aitest.etaishuo.com.myapplication.utils.FileUtils;
import aitest.etaishuo.com.myapplication.utils.JsonUtil;
import aitest.etaishuo.com.myapplication.utils.ResutBean;
import aitest.etaishuo.com.myapplication.utils.Search;
import aitest.etaishuo.com.myapplication.utils.SpUtil;
import aitest.etaishuo.com.myapplication.utils.ThreadUtils;
import okio.Source;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends BaseActivity implements EventListener {


    private boolean logTime = true;

    private TextView mTv_pingfen;




    private Button mBt_luyin;
    private Button mBt_stop;
    private String mToken;
    private Search mSearch;
    private String mSore="";
    private ListView mlv_name;


    ArrayList<String> nameData =new ArrayList<String>();

    ArrayList<String> resutData =new ArrayList<String>();



    ArrayList<String> pingFenData =new ArrayList<String>();



    ArrayList<BiJiaoBean> Source =new ArrayList<BiJiaoBean>();

    private Button mBt_yuan;
    private Button mBt_qingkong;


    int flag=0;
    private String mCid;
    private ListView mLv_restu;


    private MyAdapte mNameAdapter;


    private MyAdapte mResutAdapter;





//    class MyHander extends Handler{
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//            if(msg.what==1){
//                //语义分析拿到的结果
//                //是人名
//                Fenxi_item_bean name = (Fenxi_item_bean)msg.obj;
//
//
//                try {
//
//                  List<Score> arrayList  =   mSearch.search(name.item,10);
//
//                  String  surename =  arrayList.get(0).word.toString();
//
//                  mTv_pingfen.append("发现 最接近 的名字是 "+surename+"\n");
//
//
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            if(msg.what==2){
//             float f =   (float)msg.obj;
//             mTv_pingfen.append("积极信任度是 "+f+"\n");
//
//            }
//
//
//
//
//
//        }
//    }




    String[] Namelist = new String[100];
    private ListView mLv_pingfen;
    private MyAdapte mPingFenAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToken();
        init();

        initview();


        initData();




        dismiss();

    }

    private void initData() {

        mCid = getIntent().getExtras().getString("Cid");

        if(!mCid.equals("")){
            getClassStudentData();
        }else {
            LogUtil.e("CID 为NULL");
        }



    }

    /**
     * 初始化token
     */
    private void initToken() {

        AInet.getApi().getToken(RequestBean.getToken("2NIiEIBLFEFqXRrenP86NQgu",
                 "0OAoBGVWb14TklY0bE69WEWKdb5uQr1G")).enqueue(new Callback<TokenResutBean>() {
            @Override
            public void onResponse(Call<TokenResutBean> call, Response<TokenResutBean> response) {
                                 TokenResutBean body = response.body();
                 Log.e("code",response.code()+"");

                 String refresh_token = body.access_token;

                 ThreadUtils.runOnMainThread(new Runnable() {
                     @Override
                     public void run() {
                         mToken=refresh_token;
                         Log.e("token值是",mToken);
                     }
                 });
            }

            @Override
            public void onFailure(Call<TokenResutBean> call, Throwable t) {
                Log.e("获取token失败",t.getMessage());
            }
        });



    }

    private void init(){

      //  mMyHander = new MyHander();
//        try {
//            mSearch=new Search(Namelist);
//
//
//        } catch (PinyinException e) {
//            e.printStackTrace();
//        }
        App.getAsr().registerListener(this);

    }

    /**
     * 获取班级 信息
     */
    private void getClassStudentData() {


        String sid = SpUtil.getString(this, MainConfig.Sid, "");



        if(sid.equals("")){
            LogUtil.e("Sid 为Null");
            return;
        }

        net.getApi().getStudentsList(RequestBean.getClassAllStudentData(sid,mCid)).enqueue(new Callback<ClassBean>() {
            @Override
            public void onResponse(Call<ClassBean> call, Response<ClassBean> response) {
                ClassBean body = response.body();
                if(body!=null){
                   final ArrayList<StudentBean> message = body.message;




                    ThreadUtils.runOnMainThread(new Runnable() {
                        @Override
                        public void run() {

                            nameData.clear();

                            for(int i=0;i<message.size();i++){

                                StudentBean bean = message.get(i);

                                nameData.add(bean.studentName);

                            }


                            mNameAdapter.notifyDataSetChanged();

                            try {
                                mSearch=new Search(nameData.toArray(new String[0]));


                            } catch (PinyinException e) {
                                e.printStackTrace();
                            }

                        }
                    });





                }else {
                    LogUtil.e("拉取班级 学生信息列表  为Null");
                }


            }

            @Override
            public void onFailure(Call<ClassBean> call, Throwable t) {




            }
        });




    }

//                1536	普通话	搜索模型	无标点	不支持	默认PID
//                15361	普通话	搜索模型	无标点	支持
//                1537	普通话	输入法模型	标点可选	不支持
//


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        App.getAsr().unregisterListener(this);
    }

    private void initview() {
        mBt_luyin = findViewById(R.id.bt_luyin);

        //mTextView = findViewById(R.id.tv_text);

        mBt_yuan = (Button)findViewById(R.id.bt_yuan);
        mBt_qingkong = (Button) findViewById(R.id.bt_qingkong);


       // mTv_pingfen = (TextView) findViewById(R.id.tv_pingfen);
        mLv_pingfen = (ListView) findViewById(R.id.lv_pingfen);

        mPingFenAdapter = new MyAdapte(pingFenData);

        mLv_pingfen.setAdapter(mPingFenAdapter);





        mBt_yuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yuanjing();
                flag=2;
            }
        });



        mBt_qingkong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reSet();
            }
        });








        mlv_name = (ListView) findViewById(R.id.lv_name);
        mLv_restu = (ListView) findViewById(R.id.lv_restu);


        mNameAdapter = new MyAdapte(nameData);

        mResutAdapter = new MyAdapte(resutData);



        mlv_name.setAdapter(mNameAdapter);

        mLv_restu.setAdapter(mResutAdapter);




        //SimpleAdapter simpleAdapter = new SimpleAdapter(this,mArrayList);


//        mBt_stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 发送停止录音事件，提前结束录音等待识别结果
//                App.getAsr().send(SpeechConstant.ASR_STOP, null, null, 0, 0);
//                //asr.send(SpeechConstant.ASR_CANCE, null, null, 0, 0); // 取消本次识别，取消后将立即停止不会返回识别结果
//            }
//        });


        mBt_luyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=1;
                luyin();

            }
        });



    }

    /**
     * 情况 对话 内容
     */
    private void reSet() {
        resutData.clear();
        mResutAdapter.notifyDataSetChanged();
        //mTv_pingfen.setText("");
        pingFenData.clear();
        mPingFenAdapter.notifyDataSetChanged();

    }

    private void yuanjing() {

        LogUtil.e("调用成功");
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("PID","1936");
            //连续静音改为 2000ms
            jsonObject.put("VAD_ENDPOINT_TIMEOUT",1000);


            App.getAsr().send(SpeechConstant.ASR_START, jsonObject.toString(), null, 0, 0);



        } catch (JSONException e) {
            e.printStackTrace();
        }



    }


    public  void luyin(){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("PID","1536");
            //连续静音改为 2000ms
            jsonObject.put("VAD_ENDPOINT_TIMEOUT",1000);


            App.getAsr().send(SpeechConstant.ASR_START, jsonObject.toString(), null, 0, 0);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


//              ASR_START	String
//                (JSON结构的字符串）	json内的参数
//                    见下文“ASR_START 参数”	全部	开始一次识别。 注意不要连续调用ASR_START参数。下次调用需要在CALLBACK_EVENT_ASR_EXIT回调后，或者在ASR_CANCEL输入后。
//                    ASR_STOP			全部	停止录音
//                    ASR_CANCEL			全部	取消本次识别
//                    ASR_KWS_
//                    LOAD_ENGINE	String
//                    (JSON结构的字符串）	json内的参数
//                    见下文“ASR_KWS_LOAD_ENGINE 参数”		离线命令词
//                    ASR_KWS_
//                    UNLOAD_ENGINE			离线命令词	高级



    //name是输出事件名，params该事件的参数，(nameData,offset, length)
    // 三者一起组成额外数据。如回调的音频数据，
    // 从data[offset] 开始至data[offset + length]
    // 结束，长度为length。


    class MyAdapte extends BaseAdapter{


        ArrayList<String> data;


       public MyAdapte(ArrayList data){
           this.data=data;
       }

        @Override
        public int getCount() {
            return data==null?0:data.size();
        }

        @Override
        public String getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            MyViewHolder holder;
            if (convertView == null) {
                holder = new MyViewHolder();
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main_name_item, null);
                holder.tv_text = (TextView) convertView.findViewById(R.id.tv_text);
                convertView.setTag(holder);
            }else {
                holder = (MyViewHolder) convertView.getTag();
            }

            holder.tv_text.setText(data.get(position));


            return convertView;
        }


        class MyViewHolder{
            TextView tv_text;
        }


    }






    @Override
    public synchronized void onEvent(String name, String params, byte[] data, int offset, int length) {
        if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_READY)) {
            // 引擎就绪，可以说话，一般在收到此事件后通过UI通知用户可以说话了

        }
        if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL)) {


            ResutBean resutBean = JsonUtil.fromJson(params, ResutBean.class);
            List<String> word = resutBean.getOrigin_result().getResult().getWord();


            //最接近的
            mSore = resutBean.getResults_recognition().get(0);

            mSore = word.get(0);


            //Log.e("识别中",mSore);


            //先语法分析 ，找出 句子中的 的  名字
            //  mTextView.append("语法分析中 "+"\n");







        }
        //全部识别结束
        if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_FINISH)) {


            //Source.clear();

            int indexOf = mSore.indexOf("同学");
            //-1说明没有
            if(-1==indexOf){
                //走正常分析接口
                fenxi(mSore);
            }else {
                //对同学前面的字符串 进行处理
                try {



                    String two = mSore.substring(indexOf - 2, indexOf);


                     List<Score> twoList  =   mSearch.search(two,10);


                    Score score = twoList.get(0);




                    LogUtil.e("222222222",twoList.toString());

                    Source.add(chuli(score));

                    LogUtil.e("Source222222222222",Source.toString());

                //说明是2个字的 如果 截取3个字 会报错
                if(!(indexOf-3<0)) {
                    String three = mSore.substring(indexOf - 3, indexOf);

                    List<Score> threeList  =   mSearch.search(three,10);

                    LogUtil.e("333333333",threeList.toString());

                    //Source.add(threeList.get(0));



                    Source.add(chuli(threeList.get(0)));

                    LogUtil.e("Source23333333333333333",Source.toString());

                }
                if(!(indexOf-4<0)) {
                    String four = mSore.substring(indexOf - 4, indexOf);

                    List<Score> fourList  =   mSearch.search(four,10);

                    LogUtil.e("4444444444",fourList.toString());

                    Source.add(chuli(fourList.get(0)));

                    LogUtil.e("Source44444444444444",Source.toString());
                }

                    Collections.sort(Source);

                    LogUtil.e(Source.toString());

                    String  surename =  Source.get(0).name;


                    // mTv_pingfen.append("最相似的是 ："+surename+"\n");
                    pingFenData.add(0,"最相似的是"+"  "+surename);

                    mPingFenAdapter.notifyDataSetChanged();



                } catch (PinyinException e) {
                    e.printStackTrace();
                }


            }



           qingganfenxi(mSore);

           //pingjia(mSore);




            if(!mSore.equals("")){
                this.resutData.add(0,mSore);
                mResutAdapter.notifyDataSetChanged();
            }
//            else {
//                return;
//            }



            //flag==1?luyin():yuanjing();



            if(flag==1){
                luyin();
            }
            if(flag==2){
                yuanjing();
            }


            mSore="";


            Source.clear();


        }
        //一句话识别结束
        if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_END)) {



//            LogUtil.e("识别结束CALLBACK_EVENT_ASR_END",mSore);
//            LogUtil.e("识别结束CALLBACK_EVENT_ASR_END",params);
//            LogUtil.e("识别结束CALLBACK_EVENT_ASR_END",offset+"");
//            LogUtil.e("识别结束CALLBACK_EVENT_ASR_END",length+"");

        }
    }

    /**
     * 处理
     */
    private BiJiaoBean chuli(Score score) {

        BiJiaoBean biJiaoBean = new BiJiaoBean(score.word.toString(),score.score);

        return biJiaoBean;

    }

    /**
     * 用户 获取 纬度
     * @param text
     */
    private void pingjia(String text) {



        AInet.getApi().PingLun(RequestBean.pinglun(text),mToken,"UTF-8").enqueue(new Callback<PingLunResut_bean>() {
            @Override
            public void onResponse(Call<PingLunResut_bean> call, Response<PingLunResut_bean> response) {

                PingLunResut_bean resut_bean = response.body();



                ThreadUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {


                        if(resut_bean.items!=null){
                            ArrayList<PingLun_item> items = resut_bean.items;
                            for(PingLun_item item: items){

                                //mTv_pingfen.append("维度是  "+"\n");;
                                pingFenData.add(0,"维度是");

                                //mTv_pingfen.append(item.prop+"\n");
                                pingFenData.add(0,item.prop);
                            }
                        }else {
                            //mTv_pingfen.append("纬度分析失败 "+"\n");
                            pingFenData.add(0,"纬度分析失败 ");
                        }
                            mPingFenAdapter.notifyDataSetChanged();

                    }
                });











            }

            @Override
            public void onFailure(Call<PingLunResut_bean> call, Throwable t) {

            }
        });
    }

    /**
     *
     * 情感分析接口
     * @param text
     */
    private void qingganfenxi(String text) {

        AInet.getApi().QgSend(RequestBean.AI(text),mToken,"UTF-8").enqueue(new Callback<QgResut>() {
            @Override
            public void onResponse(Call<QgResut> call, Response<QgResut> response) {




                ThreadUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {


                        //得到的分值
                        float fraction = 0;

                        try {

                            QgResut qg = response.body();
                            if(qg!=null) {

                                ArrayList<item_bean> list = qg.items;

                                // Log.e("情感分析", list.toString());


                                //mTv_pingfen.append("情感分析得到的结果" + "\n");
                                pingFenData.add(0,"情感分析得到的结果");
                                fraction = list.get(0).positive_prob;

                                //mTv_pingfen.append(fraction + "\n");
                                pingFenData.add(0,fraction+"");

                            }else {
                                //mTv_pingfen.append("情感分析失败"+"\n");
                                pingFenData.add(0,"情感分析失败");
                            }

                            mPingFenAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //  SeedMes(fraction,2);







                    }
                });









            }


            @Override
            public void onFailure(Call<QgResut> call, Throwable t) {
                Log.e("情感分析出错",t.getMessage());
            }
        });
    }




    private void fenxi(String text) {



        AInet.getApi().CySend(RequestBean.AI(text),mToken,"UTF-8").enqueue(new Callback<FenxiResut>() {
            @Override
            public void onResponse(Call<FenxiResut> call, Response<FenxiResut> response) {
                FenxiResut body = response.body();

                if(body!=null){

                    ThreadUtils.runOnMainThread(new Runnable() {
                        @Override
                        public void run() {


                    ArrayList<Fenxi_item_bean> list = body.items;


                    //  Log.e("分析接口",list.toString());


                    //data.append("语句分析结果 ："+"\n");

                    pingFenData.add(0,"语句分析结果");


                    if(list==null){
                        return;
                    }





                            try {
                                for(Fenxi_item_bean bean:list){



                                    // 说明 这个 item是人名 拿到
                                    // PER	人名	LOC	地名	ORG	机构名	TIME	时间
                                    if(bean.ne.equals("PER")||bean.ne.equals("nr")){

                                        pingFenData.add(0,bean.item);
                                        //mTv_pingfen.append(bean.item+"\n");

                                        List<Score> arrayList  =   mSearch.search(bean.item,10);


                                        String  surename =  arrayList.get(0).word.toString();


                                       // mTv_pingfen.append("最相似的是 ："+surename+"\n");
                                        pingFenData.add(0,"最相似的是"+"  "+surename);


                                    }

                                }


                                mPingFenAdapter.notifyDataSetChanged();


                            } catch (PinyinException e) {
                                e.printStackTrace();
                            }


                        }
                    });





                }else {
                    Log.e("语句分析为NULL","666666666666666");
                    pingFenData.add(0,"获取人名失败");
                    //mTv_pingfen.append("获取人名失败"+"\n");
                    mPingFenAdapter.notifyDataSetChanged();


                }

            }

            @Override
            public void onFailure(Call<FenxiResut> call, Throwable t) {
                Log.e("语句分析出错",t.getMessage());
            }
        });

    }




//    private void SeedMes(Object obj,int what){
//       Message  msg =     new Message();
//        msg.what=what;
//        msg.obj=obj;
//        mMyHander.sendMessage(msg);
//    }

//    private void printLog(String text) {
//        if (logTime) {
//            text += "  ;time=" + System.currentTimeMillis();
//        }
//        text += "\n";
//        Log.i(getClass().getName(), text);
//        mTextView.append(text + "\n");
//    }







}
