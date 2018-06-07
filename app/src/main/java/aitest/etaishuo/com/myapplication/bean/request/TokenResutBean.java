package aitest.etaishuo.com.myapplication.bean.request;

/**
 * Created by lyh on 2018/5/23.
 */

public class TokenResutBean {

//     "refresh_token": "25.b55fe1d287227ca97aab219bb249b8ab.315360000.1798284651.282335-8574074",
//             "expires_in": 2592000,

    public String refresh_token;

    public String access_token;
    public  int expires_in;


    public  String  error;
    public  String  error_description;


    @Override
    public String toString() {
        return "TokenResutBean{" +
                "refresh_token='" + refresh_token + '\'' +
                ", expires_in=" + expires_in +
                ", error='" + error + '\'' +
                ", error_description='" + error_description + '\'' +
                '}';
    }
}
