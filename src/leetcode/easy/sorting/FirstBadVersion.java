package leetcode.easy.sorting;

public class FirstBadVersion {

    public static void main(String[] args) {
        int n = 5;
        System.out.println(firstBadVersion(n));
    }

    public static int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int firstBadVersion2(int n) {
        int first = 1;
        int last = n;
        int mid;
        int result = n;
        while(first <= last){
            mid = first+(last-first)/2;
            if(isBadVersion(mid)){
                result = mid;
                last = mid-1;
            }
            else{
                first = mid+1;
            }
        }
        return result;
    }

    public static boolean isBadVersion(int version) {
        boolean[] nums = {false, false, false, true, true};
        return nums[version - 1];
    }
}
