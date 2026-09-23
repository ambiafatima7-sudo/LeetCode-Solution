class Solution {
    public void duplicateZeros(int[] arr) {
        int zeros = 0;
        int n = arr.length;

        // Step 1: Count zeros that will fit within the original length bounds
        int i = 0;
        int j = 0; // Tracks the virtual index of the modified array
        
        while (j < n) {
            if (arr[i] == 0) {
                zeros++;
                j += 2; // A zero takes 2 spots
            } else {
                j += 1;
            }
            i++;
        }

        // i is currently at the boundary + 1 where elements will fit
        int last = i - 1;
        int writePos = n - 1;

        // Edge case: If the last zero couldn't be duplicated due to array boundary
        if (j == n + 1) {
            arr[writePos] = 0;
            writePos--;
            last--;
        }

        // Step 2: Copy elements backward to avoid overwriting unread elements
        for (int k = last; k >= 0; k--) {
            if (arr[k] == 0) {
                arr[writePos] = 0;
                arr[writePos - 1] = 0;
                writePos -= 2;
            } else {
                arr[writePos] = arr[k];
                writePos--;
            }
        }
    }
}