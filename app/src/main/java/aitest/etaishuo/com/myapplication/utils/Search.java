package aitest.etaishuo.com.myapplication.utils;



import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import aitest.etaishuo.com.myapplication.bean.Score;
import aitest.etaishuo.com.myapplication.bean.Word;

/**
 * 本文件用来演示如何通过文本相似度进行纠错
 * <p>
 * <pre>
 * String[] list = new String[]{"张三", "张衫", "张丹", "张成", "李四", "李奎"};
 * Search d = new Search(list);
 * System.out.println(d.search("张三", 10));
 * System.out.println(d.search("李四", 10));
 *
 * 输出：
 * [{word=张三, score=0}, {word=张衫, score=1}, {word=张丹, score=1}, {word=张成, score=5}, {word=李四, score=9}, {word=李奎, score=10}]
 * [{word=李四, score=0}, {word=李奎, score=3}, {word=张三, score=9}, {word=张衫, score=10}, {word=张丹, score=10}, {word=张成, score=12}]
 * </pre>
 */
public class Search {
    final List<Word> targets = new ArrayList<Word>();
    static String[] list ;
    public Search(String[] list) throws PinyinException {
        for (String s : list) {
            Word w = new Word(s);
            targets.add(w);
        }
    }
//
//    public  void init(String str[]) throws PinyinException {
//        list=str;
//    }




    public  List<Score> search(String input, int limit) throws PinyinException {
        Word w = new Word(input);
        return targets.stream().map(x -> {
            Score s = new Score();
            s.word = x;
            s.score = x.compareTo(w);
            return s;
        }).sorted().limit(limit).collect(Collectors.toList());
    }


    public static int getEditDistance(String s, String t) {
        int d[][]; // matrix
        int n; // length of s
        int m; // length of t
        int i; // iterates through s
        int j; // iterates through t
        char s_i; // ith character of s
        char t_j; // jth character of t
        int cost; // cost
        // Step 1
        n = s.length();
        m = t.length();
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];

        // Step 2
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        // Step 3
        for (i = 1; i <= n; i++) {
            s_i = s.charAt(i - 1);
            // Step 4
            for (j = 1; j <= m; j++) {
                t_j = t.charAt(j - 1);
                // Step 5
                cost = (s_i == t_j) ? 0 : 1;
                // Step 6
                d[i][j] = Minimum(d[i - 1][j] + 1, d[i][j - 1] + 1,
                        d[i - 1][j - 1] + cost);
            }
        }
        // Step 7
        return d[n][m];
    }

    private static int Minimum(int a, int b, int c) {
        int im = a < b ? a : b;
        return im < c ? im : c;
    }


}
