// 方法一：按列求
// 时间复杂度：O(n²），遍历每一列需要 n，找出左边最高和右边最高的墙加起来刚好又是一个 n，所以是 n²。
// 空间复杂度：O(1）。
public int trap1(int[] height) {
  int sum = 0;
  //最两端的列不用考虑，因为一定不会有水。所以下标从 1 到 length - 2
  for (int i = 1; i < height.length - 1; i++) {
    int max_left = 0;
    //找出左边最高
    for (int j = i - 1; j >= 0; j--) {
      if (height[j] > max_left) {
        max_left = height[j];
      }
    }
    int max_right = 0;
    //找出右边最高
    for (int j = i + 1; j < height.length; j++) {
      if (height[j] > max_right) {
        max_right = height[j];
      }
    }
    //找出两端较小的
    int min = Math.min(max_left, max_right);
    //只有较小的一段大于当前列的高度才会有水，其他情况不会有水
    if (min > height[i]) {
      sum = sum + (min - height[i]);
    }
  }
  return sum;
}


// 方法二：动态规划
// max_left [i] = Max(max_left [i-1], height[i-1])
// max_right[i] = Max(max_right[i+1], height[i+1])
// 时间复杂度：O*(*n)。
// 空间复杂度：O*(*n)，用来保存每一列左边最高的墙和右边最高的墙。
public int trap2(int[] height) {
  int sum = 0;
  int[] max_left = new int[height.length];
  int[] max_right = new int[height.length];

  for (int i = 1; i < height.length - 1; i++) {
    max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
  }
  for (int i = height.length - 2; i >= 0; i--) {
    max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
  }
  for (int i = 1; i < height.length - 1; i++) {
    int min = Math.min(max_left[i], max_right[i]);
    if (min > height[i]) {
      sum = sum + (min - height[i]);
    }
  }
  return sum;
}



// 方法三：动态规划的优化 - 双指针
public int trap3(int[] height) {
  int sum = 0;
  int max_left = 0;
  int max_right = 0;
  int left = 1;
  int right = height.length - 2; // 加右指针进去
  for (int i = 1; i < height.length - 1; i++) {
    //从左到右更
    if (height[left - 1] < height[right + 1]) {
      max_left = Math.max(max_left, height[left - 1]);
      int min = max_left;
      if (min > height[left]) {
        sum = sum + (min - height[left]);
      }
      left++;
    } 
    //从右到左更
    else {
      max_right = Math.max(max_right, height[right + 1]);
      int min = max_right;
      if (min > height[right]) {
        sum = sum + (min - height[right]);
      }
      right--;
    }
  }
  return sum;
}
