package aitest.etaishuo.com.myapplication.bean;

import java.util.ArrayList;

/**
 * Created by lyh on 2018/5/22.
 */

public class Fenxi_item_bean {


//    {
//              "byte_length":4,
//            "byte_offset":0,
//            "formal":"",
//            "activity_main_item":"百度",
//            "ne":"ORG",
//            "pos":"",
//            "uri":"",
//            "loc_details":[ ],
//        "basic_words":["百度"]
//    },


    public int byte_length;
    public int byte_offset;
    public String formal;
    public String item;
    public String ne;
    public String pos;
    public String uri;
    public ArrayList loc_details;
    public ArrayList basic_words;


    @Override
    public String toString() {
        return "Fenxi_item_bean{" +
                "byte_length=" + byte_length +"\n"+
                ", byte_offset=" + byte_offset +"\n"+
                ", formal='" + formal + '\'' +"\n"+
                ", activity_main_item='" + item + '\'' +"\n"+
                ", ne='" + ne + '\'' +"\n"+
                ", pos='" + pos + '\'' +"\n"+
                ", uri='" + uri + '\'' +"\n"+
                ", loc_details=" + loc_details +"\n"+
                ", basic_words=" + basic_words +"\n"+
                '}';
    }
}
