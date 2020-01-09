// dp 方程: sum[i] = max{sum[i-1]+a[i],a[i]}

// 最直观的想法: 先算出所有的情况,然后遍历取最大值
public int maxSubArray1(int[] nums) {
    int[] dp = new int[nums.length];
    dp[0] = nums[0];

    for (int i = 1; i < nums.length; i++) {
        dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
    }
    int max = Integer.MIN_VALUE;
    for (int i : dp) {
        max = Math.max(max, i);
    }
    return max;
}

// 优化1: 在遍历时求出最大值
public int maxSubArray2(int[] nums) {
    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    int max = nums[0];
    for (int i = 1; i < nums.length; i++) {
        dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        max = Math.max(dp[i], max);
    }
    return max;
}

// 优化2: 由于dp数组每个元素只用了一次,空间复杂度可以优化到常数级别
public int maxSubArray3(int[] nums) {
    int maxCurr = nums[0];
    int max = nums[0];
    for (int i = 1; i < nums.length; i++) {
        maxCurr = Math.max(maxCurr + nums[i], nums[i]);
        max = Math.max(max, maxCurr);
    }
    return max;
}
