package aitest.etaishuo.com.myapplication.bean;

import android.support.annotation.NonNull;

/**
 * Created by lyh on 2018/6/7.
 */

public class BiJiaoBean implements Comparable<BiJiaoBean> {

    public String name;
    public int soucre;


    public  BiJiaoBean(String name,int soucre){
        this.name=name;
        this.soucre=soucre;
    }


    @Override
    public String toString() {
        return "BiJiaoBean{" +
                "name='" + name + '\'' +
                ", soucre=" + soucre +
                '}';
    }

    @Override
    public int compareTo(@NonNull BiJiaoBean o) {
        int i = this.soucre - o.soucre;//先按相似度排序
        return i;
    }
}
