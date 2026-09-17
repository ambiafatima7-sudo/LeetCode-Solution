
import java.util.*;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;

        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        int prefixSum = 0;
        int ans = Integer.MAX_VALUE;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            prefixSum += arr[i];

            if (map.containsKey(prefixSum - target)) {
                int start = map.get(prefixSum - target);
                int length = i - start;

                if (start >= 0 && dp[start] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, length + dp[start]);
                }

                minLength = Math.min(minLength, length);
            }

            if (i > 0) {
                dp[i] = dp[i - 1];
            }

            if (prefixSum == target) {
                minLength = Math.min(minLength, i + 1);
            }

            dp[i] = Math.min(dp[i], minLength);

            map.put(prefixSum, i);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}