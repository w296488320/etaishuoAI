package aitest.etaishuo.com.myapplication.bean;

import java.util.ArrayList;

/**
 * Created by lyh on 2018/5/22.
 */

public class FenxiResut {
    public  String text;
    public ArrayList<Fenxi_item_bean> items;


    @Override
    public String toString() {
        return "FenxiResut{" +
                "text='" + text + '\'' +
                ", items=" + items +
                '}';
    }
}
