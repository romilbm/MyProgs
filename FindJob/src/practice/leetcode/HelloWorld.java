package practice.leetcode;

/**
 * Created by romilbm on 12/19/13.
 */
public class HelloWorld {
    public static void main(String args[]){
        System.out.println("Testing Isometric:"+ StringOp.isIsometric("foo", "app"));
        System.out.println("(){[]} is valid? " + StringOp.isValid("(){[]}"));
        System.out.println("((())){}{}[] is valid? " + StringOp.isValid("((())){}{}[]"));
        System.out.println("([{})] is valid? " + StringOp.isValid("([{})]"));
        System.out.println("((())){}{}[]( is valid? " + StringOp.isValid("((())){}{}[]("));
        System.out.println("((()))]{}{}[] is valid? " + StringOp.isValid("((()))]{}{}[]"));
        System.out.println("([)] is valid? " + StringOp.isValid("([)]"));

    }
}
