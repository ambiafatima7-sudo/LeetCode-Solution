class Solution {
    public boolean numofevendigit(int num){
        int countdigits=0;
        while(num!=0){
            num= num/10;
            countdigits++;
        }
        return countdigits%2==0;
    }
    public int findNumbers(int[] nums) {
       int evendigits=0;
       for(int i=0;i<nums.length;i++){
       if(numofevendigit(nums[i])){
        evendigits++;
       }
       }
       return evendigits;
    }
}