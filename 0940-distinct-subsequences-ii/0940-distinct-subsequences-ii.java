class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1000000007;

        long[] dp = new long[26];
        long total = 0;

        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) - 'a';

            long newSubsequences = (total + 1) % MOD;

            total = (total - dp[ch] + newSubsequences + MOD) % MOD;

            dp[ch] = newSubsequences;
        }

        return (int) total;
    }
}