package aitest.etaishuo.com.myapplication.utils;

import android.os.Environment;

import com.baidu.speech.utils.LogUtil;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by lyh on 2018/5/29.
 */

public class FileUtils {


    /**
     * 写入文件方法
     * @param content
     */
    public static void write(String content) {
        try {
            //判断实际是否有SD卡，且应用程序是否有读写SD卡的能力，有则返回true
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                // 获取SD卡的目录
                File sdCardDir = Environment.getExternalStorageDirectory();
                String path = "/luyin/";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File targetFile = new File(sdCardDir.getCanonicalPath() + path+"aaa.txt");
                //使用RandomAccessFile是在原有的文件基础之上追加内容，
                //而使用outputstream则是要先清空内容再写入
                RandomAccessFile raf = new RandomAccessFile(targetFile, "rw");
                //光标移到原始文件最后，再执行写入
                raf.seek(targetFile.length());
                raf.write(content.getBytes());
                raf.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("异常",e.getMessage());
        }
    }



}
