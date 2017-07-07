package practice.leetcode;

import java.util.*;

/**
 * Created by romil on 7/5/17.
 */
public class PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ret = new ArrayList<>();
        HashMap<String, HashMap<Character, Integer>> occMap = new HashMap<>();

        for (int i=0; i < words.length - 1; i++) {
            for (int j=i+1; j < words.length; j++) {
                String s1 = words[i];
                String s2 = words[j];
                int l1 = s1.length();
                int l2 = s2.length();
                HashMap<Character, Integer> charMap1 = null;
                HashMap<Character, Integer> charMap2 = null;

                if (occMap.containsKey(s1)) {
                    charMap1 = occMap.get(s1);
                } else {
                    charMap1 = getCharMap(s1);
                    occMap.put(s1, charMap1);
                }

                if (occMap.containsKey(s2)) {
                    charMap2 = occMap.get(s2);
                } else {
                    charMap2 = getCharMap(s2);
                    occMap.put(s2, charMap2);
                }

                if (l1 == l2 && !charMap1.equals(charMap2)) {
                    continue;
                }

                //compare combined occurrence.. all should have even count
                if (l1 != l2 && (l1+l2)%2 == 0 && !mayBePFromCombOcc(charMap1, charMap2, true)) {
                    continue;
                }

                //compare combined occurrence.. all except one should have even count
                if (l1 != l2 && (l1+l2)%2 == 1 && !mayBePFromCombOcc(charMap1, charMap2, false)) {
                    continue;
                }

                if (isPalindrome(s1, s2)) {
                    ret.add(new ArrayList<>(Arrays.asList(i, j)));
                }

                if (isPalindrome(s2, s1)) {
                    ret.add(new ArrayList<>(Arrays.asList(j, i)));
                }

            }
        }

        return ret;
    }

    private boolean isPalindrome(String s1, String s2) {
        int p1 = 0;
        int p2 = s2.length()-1;

        while (p1 < s1.length() && p2 >= 0) {
            if (s1.charAt(p1) != s2.charAt(p2)) return false;
            p1++;
            p2--;
        }

        if (p1 == s1.length() && p2 == -1) return true;

        if (p1 == s1.length()) return isPalindrome(s2.substring(0, p2+1));

        return isPalindrome(s1.substring(p1));
    }

    private boolean isPalindrome(String s) {
        for (int i = 0, j = s.length()-1; i<j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }

    private boolean isPalindromeOld(String s1, String s2) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append(s1);
        sb1.append(s2);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(s1);
        sb2.append(s2);
        sb2.reverse();

        return sb1.toString().equals(sb2.toString());
    }

    private boolean mayBePFromCombOcc(HashMap<Character, Integer> charMap1, HashMap<Character, Integer> charMap2, boolean isEven) {
        Set<Character> visited = new HashSet<>();
        boolean gotOddChar = false;

        for (Character c: charMap1.keySet()) {
            int combinedCount = charMap1.get(c) + (charMap2.containsKey(c) ? charMap2.get(c) : 0);
            visited.add(c);
            //if cc is even, it is fine.. always continue.
            if (combinedCount%2 == 0) continue;

            //if cc is odd, but full str is even, it can never be a palindrome
            if (isEven) return false;

            //if cc is odd, full str is odd and we already have odd char, this cannot be palindrome
            if (gotOddChar) return false;

            //if cc is odd, full str is odd and we don't have odd char, set that flag to true.
            gotOddChar = true;
        }

        for (Character c: charMap2.keySet()) {
            if (visited.contains(c)) continue;
            int combinedCount = charMap2.get(c) + (charMap1.containsKey(c) ? charMap1.get(c) : 0);
            if (combinedCount%2 == 0) continue;
            if (isEven) return false;
            if (gotOddChar) return false;
            gotOddChar = true;
        }

        return true;
    }

    private HashMap<Character, Integer> getCharMap(String s1) {
        HashMap<Character, Integer> charMap = new HashMap<>();
        for (Character c: s1.toCharArray()) {
            if (charMap.containsKey(c)) {
                charMap.put(c, charMap.get(c)+1);
            } else {
                charMap.put(c, 1);
            }
        }
        return charMap;
    }

    public static void main(String[] args) {
        PalindromePairs pp = new PalindromePairs();
        String[] words = {"bat", "tab", "cat"};
        System.out.println(pp.palindromePairs(words));

        String[] words1 = {"abcd", "dcba", "lls", "s", "sssll"};
        System.out.println(pp.palindromePairs(words1));

//        String[] words2 = {"hfjd","jfhajaadieahg","fdjddhfdcfdcehi","gfejeacaeacig","efadjahgbjffi","ehfffii","dhgccjcghjdhjhjdai","dccfcdiagjbideh","ifbajfifdea","cdhbjhhh","bjcjidhfg","jjaaihiegcaibhgg","dhghiiegceghgdabia","hihiadajddiiegc","agcicaecieaciaaigd","g","bdhfiheajbgf","degdddbfeejdcbfiif","adifdgjcade","chfjdgdgfdcbfihbfe","bfcahadcbebdjieigdhc","jfgegjhibecjjj","hciiggehjbghfgjhj","cc","ffjgeji","ibejefbjhf","ehj","jacg","feagcjbgefjbefaf","caeijg","ihbhcddh","hdhbhfacccecciffj","cjjebfeefdiddahha","gcdcgjbeieee","digghjdcfja","bagafhf","c","addgifjif","ieehbe","aiefibeececa","fjhafhcehdehj","iffdbegbbjggcghadhh","jja","igeejifibagfbhaghhaf","d","giaijchiaa","iefdiicjafhebahcd","chc","hebff","ifegfd","geaficbdhgicdicbj","ehigeifabgfhjgbi","eije","jdefbdggjbgefjehbge","aeibjeaaj","ejjhcdeddhabaacahi","cfgabfeghchegdjj","deadgbccebahd","efccbehbfdddei","ifi","agb","dacjedcchdjid","gdeji","bfijdhebbdcai","ecdhjhfafbb","cchejjgfhbf","diieggbfgjfefdbaifi","jggjdejbcgdicjgfj","egi","ih","jjchdiebja","feijafjiahhabcefficd","fgbbibgaffffcbjgccjj","bedghiidach","ecehfejeegadjdibdfac","ijgcbhiaeec","bfieifa","bhaaeag","cgaaideef","abhhbg","dcggabdbgebhgacffeee","eabhfbbbegjhhef","bib","ghgdbbbeegjcc","bheegi","i","gcafhfibafdd","hbdidcdbjibcceee","hdcgic","jc","ide","gjfbfiiggjfgfeiib","hcbgeibgbfdjecdhcfci","hdbjfefgjiafbacgife","ecfcigeiiaad","ebfh","ahecfgacfffdabj","hgidgdghj","aiiciccheiebfi","hjachjbiiaahdgi","jcdib","giceadhg","jaedhaegjejeh","headgicfcfgafahdi","bjfbaihhc","ecjfhfcdfih","jibcjhajfajibhcj","ffdagjcefihjidc","hbcdhecfecdhcbgaacbh","jid","cjibig","cahhijeafiibgadaihe","fieedidgcjej","fcea","becagjgehf","ba","jggijbbdcfeecejiicgh","abjfdfaebcbiefhb","dhdbbgjegdegfiagb","ebgafjjeibegjg","igfcjjehbhga","ehciihj","cjgadbfhaaibihcacjid","ghchcfchegibc","ddjbheajfcgcca","cji","cbhhjceijfigaibjchhe","ihbccggjggeb","ecdcicfhjbbbc","dibbbfha","jhcdjibibagdeedjcchi","ecejijcddjbgfjjaji","hjfjhj","edeacfdhjd","af","eghihebeg","ifaedbdibifigfeje","ghjfejeghebi","ahcd","ehh","iihfhbjcij","ghaccchhbgabaacg","diihgbhcbbfgagidb","iafbid","aehhdbcja","aagcggbjfdchahihab","ijhahgfce","iahhiigcaddhf","jhejjeghabi","ebfbeaifehdache","ca","haigedibehbjhja","ejgfbb","efgihgidh","edede","hjaii","dicdddjafedaa","abjcghdhgaaabaaccbic","acbjchabadajcheddbh","hgcjchhjdfi","fhfidbajiaceeific","eddghiahfijjb","hbaejbh","ghcdijacbcfichbjedh","gefcbgaefafgadigfij","jjaib","dcdhgejedcg","hfebhjeabehbhaaijhi","ghd","cagheihiaigc","bd","dccgbebbbai","f","bdbhedigci","gdciehiifefibeigfgh","icejeajchfgbiccehi","eahgb","jjfebhjhgghe","eaajbihccag","ijhjhfabccdfaghd","jcffihgdfd","hefh","ahbgfcecagiahfa","cigffhgeijh","ghcdhdgfgdhfbijgb","bdgdbeefiajehcf","a","gcjidfd","jbbeiicahdc","gdffi","gdcbagjfice","bfiigabciijcgaiih","hafeedebdif","efeaeigdaiihja","edjcgae","gcehf","aebjcfcdiagee","jddci","gejhe","bdahiicijibahfgbid","afccgaihideghi","eedfd","abafh","fgdgjddibjcejdejg","fjedhfcbeffegcbhaadj","jhicdbdiehddechf","jjgbbfdbgeegcaia","bcah","ghhgecaajbbcjge","ajagbhajgbadhhibg","hbeggafgbai","cbjhgcgaffbdhb","jib","jcjjfiebd","fgbhffaig","e","iefgbcadafbbbgjci","ihdhbjgcca","gefifbgcajcabef","ijbeicahfafagabiedg","iaceiabjd","chcejbai","edjjajiccgabghdgbei","ghgdbcdccbgfefd","iie","cdgejcabbcbhgjcjec","jbgcfbfbbeagajh","dccbb","ihcjhfjcfghgg","cahhdaeheaejfic","bgahebhbgbhdagj","gag","aeihfjadea","bbaigaj","gicic","fiecehabaajhid","cjghjdbdegbaiageifce","gdgcf","dddbjbi","bgjhdjeefbc","difgffciafjbbfiagf","icahdfedabffhacg","hjhae","hhgbabc","abbggaeibacibjafbg","gjdgfcj","cffchjjeeachefcjjh","bhcbg","hjfjfaehjdj","gjcdgjjhaji","ihfajdc","jichbjiaddadgibj","dgjefhjij","dhaed","gghgcjje","jefajijciffigceaj","jccjijc","hijbgddhccheaeaadjif","jhghabejdhcbdbfjg","jbjicjgagbejhbf","cbdb","hcgeghfeai","iecigchdgdhhii","deaihbejgbe","ejcgggebahibhiheai","jfdghjdihafgheeidchc","agibbfigaejfia","ifegddacdcjej","cjhafiebhai","dbh","bbdifjbe","cbegh","jbffbjeffhdbdib","fcbbgdjdg","hhidcjjc","bgidbagebbjcgfdccdcc","gaidc","jdfeajcafcdhec","fda","ihfaa","babhbahbicdiefea","fceiafjfchcj","afijgeeccghdiaa","hhjcdefjbfcgjcf","ciaeji","jhdedfj","bcfdjhfdhdafgfjc","ibbiaicafai","dgcicgafc","affjbchhicdacejjgde","ej","cicahbhac","ddjdcddiab","aaj","igfedigjgdgd","cbecjgeeag","fjhfdfbb","hcdghhcjfjg","ccbefai","chibjd","baghechiiieiighifi","egahgebhihcdghjaffah","gchgbeghabehheeigid","dcdejidcb","gfgjafii","agabjgehaadf","achegfgihhij","aibcifjjeahhb","hc","hebiaa","hahcfadgg","cdgcaghidgfifgibfba","jjbjijecichjjccje","jcjeiejga","b","bb","gj","cbgceh","befde","cidegbgagf","dafbb","geeafdbjjebfeiieeb","hhjdcafeaebbach","cffggdeeih","jfddjejhehe","biibchbcigcee","ibjjaah","igjjafgffded","hgbj","fbhfaajhgicgeiajibdf","gfibhaccjfh","bhhhaejhf","bebf","gfcbjfadfehegiibjha","jbihhcfgjbggi","ecbfhicbfijf","djdjehgifei","j","bdfaicdjhgdihcdi","djaaddigeeifbciehd","cchafje","ibhdeade","fggicjcaiee","afebjfbe","fchfcjfeaafej","dcegjia","hjjbaggb","hcebbchdechfj","cbi","ejgdfgfbgbgahaeihb","fiiibcfjajhdafhha","fgjab","cidc","gecd","bijgffgfjbfdecjii","eigghcbb","fefcbffeihijehc","dfahab","gffagff","fgebcghiajcdafha","hdi","fghfjia","eejcfhhcdjcdajeji","jgca","aj","ei","ejcdh","chdfdfahehdgii","eihgjaeagcjbjdbiib","ahjghheecifc","hggeeacfhceidgifbag","giebhgebeafigbgbigc","gbcif","cebcbeefbica","ggghca","gceaicb","fgjdjih","eehcdajadh","fchgdjhibjjchcccci","bdcdbbefbabjj","deeabgdibccige","dadhdifjccig","dgja","ghcahfdccbbbegdgi","bc","df","jhaebbeeagf","dajjjcdfiaifcaachhj","jhefbhgbgchbiiea","aagdjhdibabfbbebe","bggiaaeje","jhgejdg","dgjbdgbdchgf","hea","bacicfcjiifceabecfe","afaahjde","deebachgigahbac","edideehfgjbbgehgi","id","cgcffjejhdacfcccbijj","ahghcghfchfbdegdb","bhcdaijiihff","jddhg","eeaffdibdeefgfd","dgdbfhaigagbbdbjd","ib","cdfa","jfbhbiibiebffc","jigdbjcbjgedjajadb","chdaddfgifbi","da","dfjedajghfbbfgebb","cfjihhccdibegeccgjhg","aheegacijjgdgaicehfi","ahhijdgf","bgadfbaaagija","hibhebbcgfg","cefhbcahadijhgacidaa","acaegibddceicjafbbcj","bhda","gjgahdjgihbfedhiadch","fbd","ihcagdigiibggd","cdjdbdebciehifccbdf","heedhhchbhgabjafib","cjf","chdgjcgcejjid","bf","hffihgahhhbgehjdg","igbjehacfhagicd","bjefhgfcighfc","jhabf","ebacbcfchgigjbbged","hihbhgigjj","ebieeichifeicjce","gijbhebddefcgbcg","ggj","igfddeffiadgejh","gdfiidfgadbaifcihai","echghadhjaiejda","jebjbagagjjhcdfb","igifdccbii","agbjiajhbcbfachbd","bfcidhabefjjj","afeeghcicbcfbgj","fff","fcccjbjhhhbabiafdfj","hbcgcehibcdefagcf","gghdeea","ecjfffege","ihifdbe","cfiicegii","ighddeiheacjddgd","hicjfaebcjaeecddhch","jdcdjfjbhaefehahhh","cjagdcibf","jhddjdh","aaefbaggcdigehfhifii","idaiah","edbf","ciaddfcccgae","fcebgaajahbfeffcidd","dfijdgeghh","dfjfdf","eaggbeeidiiacb","fc","bjibjccdfbdf","jii","jijgfbg","fi","fedjchdcghifjife","egjfibhgahi","jcf","aeciia","edhb","eai","ficcdffe","hiedjedadiahhcbd","jdjihcfahciheaciaahd","bjcabjcgjhfbbhbagc","ajghc","iaaeajfbbadeeejfh","hfciaeacjc","igaefcdda","ccad","djhji","jagai","hdheidi","aeecab","aidjfh"};
//        System.out.println(pp.palindromePairs(words2));

        String[] words2 = {};
        System.out.println(pp.palindromePairs(words2));

        String[] words3 = {""};
        System.out.println(pp.palindromePairs(words3));

        String[] words4 = {"", ""};
        System.out.println(pp.palindromePairs(words4));

//        System.out.println(pp.isPalindrome("abcded", "cba"));
//        System.out.println(pp.isPalindrome("abc", "dedcba"));
//        System.out.println(pp.isPalindrome("abcd", "dcba"));
//
//        System.out.println(pp.isPalindrome("abcded", "cza"));
//        System.out.println(pp.isPalindrome("abc", "fedcba"));
//        System.out.println(pp.isPalindrome("abcd", "ccba"));

    }


}
