import java.util.*;

class Solution {

    long[][] dp;
    int[][][] path;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        // [left, right, weight, original index]
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by right endpoint
        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[3], b[3]);
        });

        // prev[i] = last interval ending before arr[i] starts
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {

            int left = arr[i][0];
            int lo = 0;
            int hi = i - 1;
            int ans = -1;

            while (lo <= hi) {

                int mid = lo + (hi - lo) / 2;

                if (arr[mid][1] < left) {
                    ans = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            prev[i] = ans;
        }

        // dp[k][i] = maximum score using first i intervals
        // and at most k intervals
        dp = new long[5][n + 1];

        // Store up to 4 selected original indices
        path = new int[5][n + 1][4];

        // Empty paths contain -1
        for (int k = 0; k <= 4; k++) {
            for (int i = 0; i <= n; i++) {
                Arrays.fill(path[k][i], -1);
            }
        }

        // DP
        for (int k = 1; k <= 4; k++) {

            for (int i = 1; i <= n; i++) {

                // Don't take current interval
                dp[k][i] = dp[k][i - 1];
                copy(path[k][i - 1], path[k][i]);

                // Take current interval
                int p = prev[i - 1];

                long takeScore =
                        dp[k - 1][p + 1] + arr[i - 1][2];

                int[] takePath = new int[4];
                copy(path[k - 1][p + 1], takePath);

                addIndex(takePath, arr[i - 1][3]);

                // Better score
                if (takeScore > dp[k][i]) {

                    dp[k][i] = takeScore;
                    copy(takePath, path[k][i]);

                }
                // Same score -> lexicographically smaller
                else if (takeScore == dp[k][i]) {

                    if (lexicographicallySmaller(
                            takePath, path[k][i])) {

                        copy(takePath, path[k][i]);
                    }
                }
            }
        }

        // Count selected indices
        int count = 0;

        while (count < 4 && path[4][n][count] != -1) {
            count++;
        }

        int[] answer = new int[count];

        for (int i = 0; i < count; i++) {
            answer[i] = path[4][n][i];
        }

        return answer;
    }

    // Copy 4 indices
    private void copy(int[] source, int[] destination) {

        for (int i = 0; i < 4; i++) {
            destination[i] = source[i];
        }
    }

    // Add index in sorted order
    private void addIndex(int[] arr, int value) {

        int pos = 0;

        while (pos < 4 &&
               arr[pos] != -1 &&
               arr[pos] < value) {
            pos++;
        }

        // Shift elements right
        for (int j = 3; j > pos; j--) {
            arr[j] = arr[j - 1];
        }

        arr[pos] = value;
    }

    // Check lexicographical order
    private boolean lexicographicallySmaller(int[] a, int[] b) {

        for (int i = 0; i < 4; i++) {

            if (a[i] != b[i]) {

                // -1 means no element
                if (a[i] == -1) {
                    return true;
                }

                if (b[i] == -1) {
                    return false;
                }

                return a[i] < b[i];
            }
        }

        return false;
    }
}