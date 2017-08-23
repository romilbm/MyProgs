package practice.leetcode;

import java.util.*;

/**
 * Created by romil on 7/5/17.
 */
public class PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ret = new ArrayList<>();
        if (words.length == 0) return ret;

        HashMap<String, Integer> allwords = new HashMap<>();
        int ctr = 0;
        for (String w:words) {
            allwords.put(w, ctr);
            ctr++;
        }

        for (String w: words) {
            int origindex = allwords.remove(w);
            if (w.isEmpty()) {
                ret.addAll(handleEmptyString(origindex, allwords));
                continue;
            }
            String wd = new StringBuilder(w).reverse().toString();
            for (int i=1; i<=wd.length(); i++) {
                String s1 = wd.substring(0, i);
                String s2 = wd.substring(i);

                if (allwords.containsKey(s1) && isPalindrome(s2)) {
                    ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(allwords.get(s1), origindex));
                    ret.add(temp);
                }

                if (allwords.containsKey(s2) && isPalindrome(s1) && !s2.isEmpty()) {
                    ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(origindex, allwords.get(s2)));
                    ret.add(temp);
                }
            }
            allwords.put(w, origindex);
        }

        return ret;
    }

    private Set<List<Integer>> handleEmptyString(int origindex, HashMap<String, Integer> allwords) {
        Set<List<Integer>> ret = new HashSet<>();
        for (String w: allwords.keySet()) {
            if (!isPalindrome(w)) continue;
            ret.add(new ArrayList<>(Arrays.asList(origindex, allwords.get(w))));
            ret.add(new ArrayList<>(Arrays.asList(allwords.get(w), origindex)));
        }
        return ret;
    }

    private boolean isPalindrome(String s) {
//        if (s.isEmpty()) return true;
        for (int i = 0, j = s.length()-1; i<j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePairs pp = new PalindromePairs();
//        String[] words = {"bat", "tab", "cat"};
//        System.out.println(pp.palindromePairs(words));

//        String[] words1 = {"abcd", "dcba", "lls", "s", "sssll"};
//        System.out.println(pp.palindromePairs(words1));

//        String[] words2 = {"hfjd","jfhajaadieahg","fdjddhfdcfdcehi","gfejeacaeacig","efadjahgbjffi","ehfffii","dhgccjcghjdhjhjdai","dccfcdiagjbideh","ifbajfifdea","cdhbjhhh","bjcjidhfg","jjaaihiegcaibhgg","dhghiiegceghgdabia","hihiadajddiiegc","agcicaecieaciaaigd","g","bdhfiheajbgf","degdddbfeejdcbfiif","adifdgjcade","chfjdgdgfdcbfihbfe","bfcahadcbebdjieigdhc","jfgegjhibecjjj","hciiggehjbghfgjhj","cc","ffjgeji","ibejefbjhf","ehj","jacg","feagcjbgefjbefaf","caeijg","ihbhcddh","hdhbhfacccecciffj","cjjebfeefdiddahha","gcdcgjbeieee","digghjdcfja","bagafhf","c","addgifjif","ieehbe","aiefibeececa","fjhafhcehdehj","iffdbegbbjggcghadhh","jja","igeejifibagfbhaghhaf","d","giaijchiaa","iefdiicjafhebahcd","chc","hebff","ifegfd","geaficbdhgicdicbj","ehigeifabgfhjgbi","eije","jdefbdggjbgefjehbge","aeibjeaaj","ejjhcdeddhabaacahi","cfgabfeghchegdjj","deadgbccebahd","efccbehbfdddei","ifi","agb","dacjedcchdjid","gdeji","bfijdhebbdcai","ecdhjhfafbb","cchejjgfhbf","diieggbfgjfefdbaifi","jggjdejbcgdicjgfj","egi","ih","jjchdiebja","feijafjiahhabcefficd","fgbbibgaffffcbjgccjj","bedghiidach","ecehfejeegadjdibdfac","ijgcbhiaeec","bfieifa","bhaaeag","cgaaideef","abhhbg","dcggabdbgebhgacffeee","eabhfbbbegjhhef","bib","ghgdbbbeegjcc","bheegi","i","gcafhfibafdd","hbdidcdbjibcceee","hdcgic","jc","ide","gjfbfiiggjfgfeiib","hcbgeibgbfdjecdhcfci","hdbjfefgjiafbacgife","ecfcigeiiaad","ebfh","ahecfgacfffdabj","hgidgdghj","aiiciccheiebfi","hjachjbiiaahdgi","jcdib","giceadhg","jaedhaegjejeh","headgicfcfgafahdi","bjfbaihhc","ecjfhfcdfih","jibcjhajfajibhcj","ffdagjcefihjidc","hbcdhecfecdhcbgaacbh","jid","cjibig","cahhijeafiibgadaihe","fieedidgcjej","fcea","becagjgehf","ba","jggijbbdcfeecejiicgh","abjfdfaebcbiefhb","dhdbbgjegdegfiagb","ebgafjjeibegjg","igfcjjehbhga","ehciihj","cjgadbfhaaibihcacjid","ghchcfchegibc","ddjbheajfcgcca","cji","cbhhjceijfigaibjchhe","ihbccggjggeb","ecdcicfhjbbbc","dibbbfha","jhcdjibibagdeedjcchi","ecejijcddjbgfjjaji","hjfjhj","edeacfdhjd","af","eghihebeg","ifaedbdibifigfeje","ghjfejeghebi","ahcd","ehh","iihfhbjcij","ghaccchhbgabaacg","diihgbhcbbfgagidb","iafbid","aehhdbcja","aagcggbjfdchahihab","ijhahgfce","iahhiigcaddhf","jhejjeghabi","ebfbeaifehdache","ca","haigedibehbjhja","ejgfbb","efgihgidh","edede","hjaii","dicdddjafedaa","abjcghdhgaaabaaccbic","acbjchabadajcheddbh","hgcjchhjdfi","fhfidbajiaceeific","eddghiahfijjb","hbaejbh","ghcdijacbcfichbjedh","gefcbgaefafgadigfij","jjaib","dcdhgejedcg","hfebhjeabehbhaaijhi","ghd","cagheihiaigc","bd","dccgbebbbai","f","bdbhedigci","gdciehiifefibeigfgh","icejeajchfgbiccehi","eahgb","jjfebhjhgghe","eaajbihccag","ijhjhfabccdfaghd","jcffihgdfd","hefh","ahbgfcecagiahfa","cigffhgeijh","ghcdhdgfgdhfbijgb","bdgdbeefiajehcf","a","gcjidfd","jbbeiicahdc","gdffi","gdcbagjfice","bfiigabciijcgaiih","hafeedebdif","efeaeigdaiihja","edjcgae","gcehf","aebjcfcdiagee","jddci","gejhe","bdahiicijibahfgbid","afccgaihideghi","eedfd","abafh","fgdgjddibjcejdejg","fjedhfcbeffegcbhaadj","jhicdbdiehddechf","jjgbbfdbgeegcaia","bcah","ghhgecaajbbcjge","ajagbhajgbadhhibg","hbeggafgbai","cbjhgcgaffbdhb","jib","jcjjfiebd","fgbhffaig","e","iefgbcadafbbbgjci","ihdhbjgcca","gefifbgcajcabef","ijbeicahfafagabiedg","iaceiabjd","chcejbai","edjjajiccgabghdgbei","ghgdbcdccbgfefd","iie","cdgejcabbcbhgjcjec","jbgcfbfbbeagajh","dccbb","ihcjhfjcfghgg","cahhdaeheaejfic","bgahebhbgbhdagj","gag","aeihfjadea","bbaigaj","gicic","fiecehabaajhid","cjghjdbdegbaiageifce","gdgcf","dddbjbi","bgjhdjeefbc","difgffciafjbbfiagf","icahdfedabffhacg","hjhae","hhgbabc","abbggaeibacibjafbg","gjdgfcj","cffchjjeeachefcjjh","bhcbg","hjfjfaehjdj","gjcdgjjhaji","ihfajdc","jichbjiaddadgibj","dgjefhjij","dhaed","gghgcjje","jefajijciffigceaj","jccjijc","hijbgddhccheaeaadjif","jhghabejdhcbdbfjg","jbjicjgagbejhbf","cbdb","hcgeghfeai","iecigchdgdhhii","deaihbejgbe","ejcgggebahibhiheai","jfdghjdihafgheeidchc","agibbfigaejfia","ifegddacdcjej","cjhafiebhai","dbh","bbdifjbe","cbegh","jbffbjeffhdbdib","fcbbgdjdg","hhidcjjc","bgidbagebbjcgfdccdcc","gaidc","jdfeajcafcdhec","fda","ihfaa","babhbahbicdiefea","fceiafjfchcj","afijgeeccghdiaa","hhjcdefjbfcgjcf","ciaeji","jhdedfj","bcfdjhfdhdafgfjc","ibbiaicafai","dgcicgafc","affjbchhicdacejjgde","ej","cicahbhac","ddjdcddiab","aaj","igfedigjgdgd","cbecjgeeag","fjhfdfbb","hcdghhcjfjg","ccbefai","chibjd","baghechiiieiighifi","egahgebhihcdghjaffah","gchgbeghabehheeigid","dcdejidcb","gfgjafii","agabjgehaadf","achegfgihhij","aibcifjjeahhb","hc","hebiaa","hahcfadgg","cdgcaghidgfifgibfba","jjbjijecichjjccje","jcjeiejga","b","bb","gj","cbgceh","befde","cidegbgagf","dafbb","geeafdbjjebfeiieeb","hhjdcafeaebbach","cffggdeeih","jfddjejhehe","biibchbcigcee","ibjjaah","igjjafgffded","hgbj","fbhfaajhgicgeiajibdf","gfibhaccjfh","bhhhaejhf","bebf","gfcbjfadfehegiibjha","jbihhcfgjbggi","ecbfhicbfijf","djdjehgifei","j","bdfaicdjhgdihcdi","djaaddigeeifbciehd","cchafje","ibhdeade","fggicjcaiee","afebjfbe","fchfcjfeaafej","dcegjia","hjjbaggb","hcebbchdechfj","cbi","ejgdfgfbgbgahaeihb","fiiibcfjajhdafhha","fgjab","cidc","gecd","bijgffgfjbfdecjii","eigghcbb","fefcbffeihijehc","dfahab","gffagff","fgebcghiajcdafha","hdi","fghfjia","eejcfhhcdjcdajeji","jgca","aj","ei","ejcdh","chdfdfahehdgii","eihgjaeagcjbjdbiib","ahjghheecifc","hggeeacfhceidgifbag","giebhgebeafigbgbigc","gbcif","cebcbeefbica","ggghca","gceaicb","fgjdjih","eehcdajadh","fchgdjhibjjchcccci","bdcdbbefbabjj","deeabgdibccige","dadhdifjccig","dgja","ghcahfdccbbbegdgi","bc","df","jhaebbeeagf","dajjjcdfiaifcaachhj","jhefbhgbgchbiiea","aagdjhdibabfbbebe","bggiaaeje","jhgejdg","dgjbdgbdchgf","hea","bacicfcjiifceabecfe","afaahjde","deebachgigahbac","edideehfgjbbgehgi","id","cgcffjejhdacfcccbijj","ahghcghfchfbdegdb","bhcdaijiihff","jddhg","eeaffdibdeefgfd","dgdbfhaigagbbdbjd","ib","cdfa","jfbhbiibiebffc","jigdbjcbjgedjajadb","chdaddfgifbi","da","dfjedajghfbbfgebb","cfjihhccdibegeccgjhg","aheegacijjgdgaicehfi","ahhijdgf","bgadfbaaagija","hibhebbcgfg","cefhbcahadijhgacidaa","acaegibddceicjafbbcj","bhda","gjgahdjgihbfedhiadch","fbd","ihcagdigiibggd","cdjdbdebciehifccbdf","heedhhchbhgabjafib","cjf","chdgjcgcejjid","bf","hffihgahhhbgehjdg","igbjehacfhagicd","bjefhgfcighfc","jhabf","ebacbcfchgigjbbged","hihbhgigjj","ebieeichifeicjce","gijbhebddefcgbcg","ggj","igfddeffiadgejh","gdfiidfgadbaifcihai","echghadhjaiejda","jebjbagagjjhcdfb","igifdccbii","agbjiajhbcbfachbd","bfcidhabefjjj","afeeghcicbcfbgj","fff","fcccjbjhhhbabiafdfj","hbcgcehibcdefagcf","gghdeea","ecjfffege","ihifdbe","cfiicegii","ighddeiheacjddgd","hicjfaebcjaeecddhch","jdcdjfjbhaefehahhh","cjagdcibf","jhddjdh","aaefbaggcdigehfhifii","idaiah","edbf","ciaddfcccgae","fcebgaajahbfeffcidd","dfijdgeghh","dfjfdf","eaggbeeidiiacb","fc","bjibjccdfbdf","jii","jijgfbg","fi","fedjchdcghifjife","egjfibhgahi","jcf","aeciia","edhb","eai","ficcdffe","hiedjedadiahhcbd","jdjihcfahciheaciaahd","bjcabjcgjhfbbhbagc","ajghc","iaaeajfbbadeeejfh","hfciaeacjc","igaefcdda","ccad","djhji","jagai","hdheidi","aeecab","aidjfh"};
//        System.out.println(pp.palindromePairs(words2));

        String[] words2 = {"a","b","c","ab","ac","aa"};
        System.out.println(pp.palindromePairs(words2));
//
        String[] words3 = {"a", ""};
        System.out.println(pp.palindromePairs(words3));
//
//        String[] words4 = {"", ""};
//        System.out.println(pp.palindromePairs(words4));

//        System.out.println(pp.isPalindrome("abcded", "cba"));
//        System.out.println(pp.isPalindrome("abc", "dedcba"));
//        System.out.println(pp.isPalindrome("abcd", "dcba"));
//
//        System.out.println(pp.isPalindrome("abcded", "cza"));
//        System.out.println(pp.isPalindrome("abc", "fedcba"));
//        System.out.println(pp.isPalindrome("abcd", "ccba"));

        int[] a = new int[] {};

    }


}
