// Time Complexity : O(n log k) with worse case O(n log m) where m is the length of source string and n is the lenght of target string and k is the average occurrence of each char in source string
// Space Complexity : O(m)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach

/**
 * Store the occurrence of each character in the source string.
 * Start matching one character of target string at a time using pointers on each string starting at 0th index
 *  If the target character doesn't exist in source string, return -1 since target cannot be made.
 *  Perform a binary search on the occurrence list from source string for the current target character
 *      If the java native binary search function returned a positive number, move the starting pointer to that index since it was an exact match
 *      If the java native binary search function returned the size of the list, then set the starting pointer to first occurrence of that character and increase the count by 1 since there were not any remaining chars left in source string to be used
 *      If the java native binary search function returned a negative number, then move the starting pointer to (-num-1) to get the next closest biggest match.
 *  At the end of each loop, check if target pointer's size was equal to targetLength. We return the result.
 *  At the end of each loop, check if the source pointer's size was equal to sourceLength, We increase the result count by 1 and move the source pointer back to 0 since we will have to use the source string again.
 */
class Solution {
    public int shortestWay(String source, String target) {
        Map<Character, List<Integer>> map = new HashMap<>();
        int sLength = source.length();
        int tLength = target.length();
        // creating the map of character and occurrence in source string
        for (int i = 0; i < sLength; i++) {
            char ch = source.charAt(i);
            if (!map.containsKey(ch)) {
                map.put(ch, new ArrayList<>());
            }
            map.get(ch).add(i);
        }

        int sp = 0;
        int tp = 0;
        int cnt = 0;
        while (tp < tLength) {
            char tChar = target.charAt(tp);
            // target has character which is not present in source
            if (!map.containsKey(tChar)) {
                return -1;
            }

            List<Integer> li = map.get(tChar);
            // Using binary search to find the index of target char in source string from last pointer
            int idx = Collections.binarySearch(li, sp);

            // not exact but next closest bigger match found
            if (idx < 0) {
                idx = -idx - 1;
            }

            // no match was found. Increasing the count by 1 since we will have to start using the source string again from this character
            if (idx == li.size()) {
                sp = li.get(0);
                cnt++;
            }
            // exact match was found
            else {
                sp = li.get(idx);
            }
            sp++;
            tp++;

            // found all characters in target string
            if (tp == tLength) {
                return cnt + 1;
            }

            // at last character of source string. Increasing the count by 1 since we will have to start using the source string again
            if (sp == sLength) {
                sp = 0;
                cnt++;
            }
        }

        // unreachable
        return -1;
    }
}