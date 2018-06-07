package aitest.etaishuo.com.myapplication.bean;

import java.util.ArrayList;

/**
 * Created by lyh on 2018/5/22.
 */

public class QgResut {
    public String text;
    public ArrayList<aitest.etaishuo.com.myapplication.bean.request.item_bean> items;


    @Override
    public String toString() {
        return "QgResut{" +
                "text='" + text + '\'' +
                ", items=" + items +
                '}';
    }
}
