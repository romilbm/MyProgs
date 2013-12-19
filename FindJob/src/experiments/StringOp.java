package experiments;

import java.util.HashMap;
import static java.lang.String.valueOf;

/**
 * Created by romilbm on 12/19/13.
 */
public class StringOp {

    public static boolean isIsometric(String s1, String s2){
        if(s1.length() != s2.length()){
            return false;
        }
        HashMap<String, String> m = new HashMap<String, String>();

        for(int i=0; i < s1.length(); i++){
            if(m.containsKey(valueOf(s1.charAt(i)))){
                if(!m.get(valueOf(s1.charAt(i))).equals(valueOf(s2.charAt(i)))){
                    return false;
                }

            } else {
                m.put(valueOf(s1.charAt(i)), valueOf(s2.charAt(i)));
            }
        }
        return true;
    }
}
