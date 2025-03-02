package practicum.interview.old;

/*
1)
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]

2)
Input: list1 = [], list2 = []
Output: []

3)
Input: list1 = [], list2 = [0]
Output: [0]
 */

public class MergeTwoSortedLists {

    public static void main(String[] args) {
        int[] list2 = {1,2,4};
        int[] list1 = {1,3,4};

        ListNode head1 = null;
        for (int i = list1.length - 1; i >= 0; i--) {
            head1 = new ListNode(list1[i], head1);
        }

        ListNode head2 = null;
        for (int i = list2.length - 1; i >= 0; i--) {
            head2 = new ListNode(list2[i], head2);
        }

        ListNode head3 = mergeTwoLists(head1, head2);

        System.out.print("|");
        while (head3 != null) {
            System.out.print(head3.val + "|");
            head3 = head3.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 != null ? list1 : list2;
        }
        ListNode main;
        ListNode branch;
        if (list1.val <= list2.val) {
            main = list1;
            branch = list2;
        } else {
            main = list2;
            branch = list1;
        }
        ListNode head = main;

        while (main != null) {
            if (main.next != null && branch != null && branch.val < main.next.val) {
                ListNode tempNextMain = main.next;
                ListNode tempNextBranch = branch.next;
                main.next = branch;
                main.next.next = tempNextMain;
                branch = tempNextBranch;
            } else if (main.next == null && branch != null) {
                main.next = branch;
                break;
            }
            main = main.next;
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
