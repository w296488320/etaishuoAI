package aitest.etaishuo.com.myapplication.bean.request;

/**
 * Created by lyh on 2018/5/22.
 */

public class item_bean {

//            "sentiment":2,    //表示情感极性分类结果
//             "confidence":0.40, //表示分类的置信度
//             "positive_prob":0.73, //表示属于积极类别的概率
//             "negative_prob":0.27  //表示属于消极类别的概率

    public int sentiment;
    public float confidence;
    public float positive_prob;
    public float negative_prob;


    @Override
    public String toString() {
        return "item_bean{" +
                "sentiment=" + sentiment +
                ", confidence=" + confidence +
                ", positive_prob=" + positive_prob +
                ", negative_prob=" + negative_prob +
                '}';
    }
}
