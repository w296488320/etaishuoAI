package aitest.etaishuo.com.myapplication.bean;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import aitest.etaishuo.com.myapplication.utils.Search;

import static aitest.etaishuo.com.myapplication.utils.Search.getEditDistance;

/**
 * Created by lyh on 2018/5/22.
 */

public class Word implements Comparable {

    final String word;
    final String pinyin1;
    final String pinyin2;

    public Word(String word) throws PinyinException {
        this.word = word;
        this.pinyin1 = PinyinHelper.convertToPinyinString(word, ",", PinyinFormat.WITH_TONE_NUMBER);
        this.pinyin2 = PinyinHelper.convertToPinyinString(word, ",", PinyinFormat.WITHOUT_TONE);
    }

    @Override
    public String toString() {
        return word;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Word) {
            Word o1 = (Word) o;
            int score1 = getEditDistance(this.pinyin1, o1.pinyin1);
            int score2 = getEditDistance(this.pinyin2, o1.pinyin2);
            return score1 + score2;
        }
        return 0;
    }
}
