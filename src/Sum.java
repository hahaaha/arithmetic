public class Sum {
    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    // 计算arr这个区间内所有数字的和
    private static int sum(int[] arr, int l) {
        if(l == arr.length)
            return 0;
        return arr[l] + sum(arr,l+1);
    }
}
