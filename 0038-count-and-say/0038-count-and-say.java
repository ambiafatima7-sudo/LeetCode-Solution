class Solution {
    public String countAndSay(int n) {
          String s = "1";

        for (int i = 1; i < n; i++) {
            StringBuilder result = new StringBuilder();
            int j = 0;

            while (j < s.length()) {
                int count = 1;

                while (j + 1 < s.length() && s.charAt(j) == s.charAt(j + 1)) {
                    count++;
                    j++;
                }

                result.append(count);
                result.append(s.charAt(j));
                j++;
            }

            s = result.toString();
        }

        return s;
    }
}