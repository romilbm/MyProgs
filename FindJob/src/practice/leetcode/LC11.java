package practice.leetcode;

/**
 * Container With Most Water
 */
public class LC11 {
    public static int maxArea(int[] height) {
        int l = 0, r = height.length-1;
        int maxArea = Math.min(height[l], height[r]) * (height.length - 1);

        //keep doing till left = right
        while (l != r) {
            //start from current l
            //move to right and get to newl such that it is larger than current l
            //but not reached r
            int newl = l;
            while (height[newl] <= height[l] && newl != r) {
                newl++;
            }
            //get the area with newl
            int newAreaLside = (r-newl) * Math.min(height[newl], height[r]);

            //similarly do for r
            int newr = r;
            while (height[newr] <= height[r] && newr != l) {
                newr--;
            }
            int newAreaRSide = (newr-l) * Math.min(height[l], height[newr]);

            //now compare l side and r side.. which ever is greater move the l or r pointer there.
            //compare with current max area everytime.
            if (newAreaRSide > newAreaLside) {
                maxArea = Math.max(newAreaRSide, maxArea);
                r = newr;
            } else {
                maxArea = Math.max(newAreaLside, maxArea);
                l = newl;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] ip = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(ip));
    }
}
