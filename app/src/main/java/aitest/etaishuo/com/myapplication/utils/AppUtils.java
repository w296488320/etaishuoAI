package aitest.etaishuo.com.myapplication.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import java.io.File;
import java.util.List;

import aitest.etaishuo.com.myapplication.App;

/**
 * AppUtils
 *
 * @author Owen Liang
 * @since 2014-1-13 下午5:04:01
 */
public class AppUtils {



    public static int getVersionCode() {
        String pkName = App.context.getPackageName();
        int versionCode = 0;
        try {
            versionCode = App.context.getPackageManager()
                    .getPackageInfo(pkName, 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static String getVersionName() {
        String pkName = App.context.getPackageName();
        String versionName = "";
        try {
            versionName = App.context.getPackageManager()
                    .getPackageInfo(pkName, 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * device model name, e.g: GT-I9100
     *
     * @return the user_Agent
     */
    public static String getDevice() {
        return Build.MODEL;
    }


    /**
     * device factory name, e.g: Samsung
     *
     * @return the vENDOR
     */
    public static String getVendor() {
        return Build.BRAND;
    }


    /**
     * @return the SDK version
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }



}
