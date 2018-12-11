package com.dj.custom.tag;

import java.util.Random;

/**
 * Created by xud on 2018/12/7
 */
public class TagGenarator {

    public static final String WORDS = "明月几时有把酒问青天不知天上宫阙今夕是何年我欲乘风归去又恐琼楼玉宇高处不胜寒起舞弄清影何似在人间转朱阁低绮户照无眠不应有恨何事长向别时圆人有悲欢离合月有阴晴圆缺此事古难全但愿人长久千里共婵娟";

    public static String[] generate(int count, int maxTagCount) {
        String[] tags = new String[count];
        int maxLength = WORDS.length();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int tagLen = 2 + random.nextInt(maxTagCount);
            int beginIndex = random.nextInt(maxLength - tagLen - 1);
            tags[i] = WORDS.substring(beginIndex, beginIndex + tagLen);
        }
        return tags;
    }
}
