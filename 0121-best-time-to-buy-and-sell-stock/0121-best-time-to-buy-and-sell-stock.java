class Solution {
    public int maxProfit(int[] prices) {
        int minprice = prices[0];
        int maxprofit = 0;

        for (int price : prices) {
            if (price < minprice) {
                minprice = price;
            }

            if (price - minprice > maxprofit) {
                maxprofit = price - minprice;
            }
        }

        return maxprofit;
    }
}