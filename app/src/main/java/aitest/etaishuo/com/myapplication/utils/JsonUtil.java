package aitest.etaishuo.com.myapplication.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mindx on 2017/4/27.
 */

public class JsonUtil {
    public static String toString(Object o) {
        try {
            return new Gson().toJson(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T fromJson(String str, Class<T> c) {
        try {
            return new Gson().fromJson(str, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
