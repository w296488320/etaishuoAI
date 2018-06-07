package aitest.etaishuo.com.myapplication.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/19.
 */

public class ClassBean {



    public String protocol;
    public boolean result;
    public ArrayList<StudentBean> message;


    @Override
    public String toString() {
        return "ClassBean{" +
                "protocol='" + protocol + '\'' +
                ", result=" + result +
                ", message=" + message +
                '}';
    }
}
