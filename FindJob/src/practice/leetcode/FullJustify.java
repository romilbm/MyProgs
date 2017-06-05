package practice.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romil on 5/16/17.
 */
public class FullJustify {

    public List<String> fullJustify(String[] words, int maxWidth) {
        int ctr1 = 0;
        int ctr2 = -1;
        List<String> justified = new ArrayList<String>();

        for (ctr1 = 0, ctr2 = ctr1; ctr1 < words.length;) {
            if(words[ctr1].length() == 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < maxWidth; i++) {
                    sb.append(' ');
                }
                justified.add(sb.toString());
                ctr2++;
                ctr1++;
                continue;
            }

            int curLen = 0;
            int actualLen = 0;
            int lastLen = 0;
            while (curLen <= maxWidth && ctr2 < words.length) {
                actualLen += words[ctr2].length();
                curLen += words[ctr2].length() + 1;
                lastLen = words[ctr2].length();
                ctr2++;
            }
            int numOfWords = (ctr2 - ctr1);
            if (curLen-1 > maxWidth && numOfWords != 1) {
                ctr2--;
                actualLen -= lastLen;
            }
            String buildLine;
            if(ctr2 < words.length) {
                buildLine = getJustifiedLine(words, ctr1, ctr2, maxWidth, actualLen);
            } else {
                buildLine = getJustifiedLine2(words, ctr1, ctr2, maxWidth, actualLen);
            }
            justified.add(buildLine);
            ctr1 = ctr2;
        }

        return justified;
    }

    private String getJustifiedLine(String[] words, int ctr1, int ctr2, int maxWidth, int actualLen) {
        StringBuilder sb = new StringBuilder();
        int st = ctr1;
        int spaceToFill = maxWidth - actualLen;
        int numOfWords = (ctr2 - ctr1);
        int spacePerWord = 0;
        if (numOfWords > 1) {
            spacePerWord = spaceToFill / (numOfWords - 1);
        }
        sb.append(words[st]);
        st++;
        while(st < ctr2) {
            for (int i = 0; i < spacePerWord && spaceToFill > 0; i++) {
                sb.append(' ');
                spaceToFill--;
            }
            sb.append(words[st]);
            st++;
        }

        st = ctr1;
        int offset = 0;
        while (spaceToFill > 0 && st < ctr2) {
            offset += words[st].length() + spacePerWord;
            sb.insert(offset, ' ');
            spaceToFill--;
            offset ++;
            st++;
        }

        while(spaceToFill > 0) {
            sb.append(' ');
            spaceToFill--;
        }

        return sb.toString();
    }

    private String getJustifiedLine2(String[] words, int ctr1, int ctr2, int maxWidth, int actualLen) {
        StringBuilder sb = new StringBuilder();
        int st = ctr1;
        int spaceToFill = maxWidth - actualLen;

        sb.append(words[st]);
        st++;
        while(st < ctr2) {
            sb.append(' ');
            spaceToFill--;
            sb.append(words[st]);
            st++;
        }

        while(spaceToFill > 0) {
            sb.append(' ');
            spaceToFill--;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        FullJustify fj = new FullJustify();
        String[] ip = {"a","b","c","d","e"};
        int width = 3;
        ip = new String[]{"Listen","to","many,","speak","to","a","few."};
        width = 6;
        List<String> s = fj.fullJustify(ip, width);
        System.out.println(s);
    }
}
