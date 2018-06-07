package aitest.etaishuo.com.myapplication.bean;

/**
 * Created by lyh on 2018/5/23.
 */

public class getTokenBean {


    public  String  grant_type;
    public  String  client_id;
    public  String  client_secret;

    @Override
    public String toString() {
        return "getTokenBean{" +
                "grant_type='" + grant_type + '\'' +
                ", client_id='" + client_id + '\'' +
                ", client_secret='" + client_secret + '\'' +
                '}';
    }
}
