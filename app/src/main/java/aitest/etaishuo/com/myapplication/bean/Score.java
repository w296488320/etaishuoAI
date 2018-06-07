package aitest.etaishuo.com.myapplication.bean;

import aitest.etaishuo.com.myapplication.utils.Search;

/**
 * Created by lyh on 2018/5/22.
 */

public class Score implements Comparable{

    public Word word;
    public int score;

    @Override
    public int compareTo(Object o) {
        if (o instanceof Score) {
            return score - ((Score) o).score;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "{" +
                "word=" + word +
                ", score=" + score +
                '}';
    }
}
