// Time Complexity : O(2n) where n is the number of elements in the input array
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach

/**
 * All dominos can have same number on either top of bottom after rotation only when the number exists in the first or any position of top or bottom.
 * Starting with first number on top, get the count of minimum number of rotations required
 *  If a positive result was found using this number, return the result.
 *  If the result was negative, then the current target number from source didn't match in both tops and bottoms at the index. Attempt to find the result using first number from bottoms array and return its response since we have tried first number from both top and bottom
 */
class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        // check the rotation count for first number on tops array
        int re = check(tops, bottoms, tops[0]);
        // if result was found, then return the result
        if (re != -1) return re;

        // if the result was not found, then it was not possible to make the rotations using the first number from tops array
        // it can happen only when the first number from tops array was not the target number
        // find the result for first number from bottoms array and return it
        return check(tops, bottoms, bottoms[0]);
    }

    private int check(int[] tops, int[] bottoms, int target) {
        int tRot = 0;
        int bRot = 0;
        for (int i = 0; i < tops.length; i++) {
            // both top and bottom didn't have the target number, so dominos can't be aligned
            if (tops[i] != target && bottoms[i] != target) {
                return -1;
            }

            // top didn't match the target number, increasing the count for top rotation
            if (tops[i] != target) {
                tRot++;
            }

            // bottom didn't match the target number, increasing the count for bottom rotation
            if (bottoms[i] != target) {
                bRot++;
            }
        }

        // returning the minimum number of rotations between top rotation and bottom rotations
        return Math.min(tRot, bRot);
    }
}