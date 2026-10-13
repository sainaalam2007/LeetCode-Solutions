
import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        long[] sq = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            sq[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(sq);

        int n = sq.length;
        long ans = 0;

        for (int i = 0; i < n; i++) {
            if (i < n / 2) {
                ans -= sq[i];
            } else {
                ans += sq[i];
            }
        }

        return ans;
    }
}
