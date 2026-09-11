class Solution {
    public int totalNumbers(int[] digits) {
         int[] count = new int[10];

        // Count frequency of each digit
        for (int d : digits) {
            count[d]++;
        }

        int ans = 0;

        // Try every 3-digit number
        for (int i = 1; i <= 9; i++) {          // hundreds digit
            for (int j = 0; j <= 9; j++) {      // tens digit
                for (int k = 0; k <= 8; k += 2) { // units digit
                   
                    // Check if digits are available
                    int[] used = new int[10];

                    used[i]++;
                    used[j]++;
                    used[k]++;

                    boolean possible = true;

                    for (int d = 0; d <= 9; d++) {
                        if (used[d] > count[d]) {
                            possible = false;
                            break;
                        }
                    }

                    if (possible) {
                        ans++;
                    }
                }
            }
        }

        return ans;
    }
}