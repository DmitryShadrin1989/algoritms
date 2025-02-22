package practicum.interview.old;

/*
1)
Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {

    public static void main(String[] args) {
        int[][] lists = {{1,4,5},{1,3,4},{2,6}};
        ListNode[] nodeLists = new ListNode[lists.length];
        int nodeListsIdx = 0;
        for (int i = 0; i < lists.length; i++) {
            int[] nodes = lists[i];
            if (nodes.length == 0) {
                continue;
            }
            if (nodes.length == 1) {
                nodeLists[nodeListsIdx] = new ListNode(nodes[0]);
                nodeListsIdx++;
                continue;
            }
            ListNode headNode = new ListNode(nodes[0]);
            ListNode tailNode = headNode;
            for (int j = 1; j < nodes.length; j++) {
                ListNode newNode = new ListNode(nodes[j]);
                tailNode.next = newNode;
                tailNode = newNode;
            }
            nodeLists[nodeListsIdx] = headNode;
            nodeListsIdx++;
        }

        ListNode mergeKList = mergeKListsUsePriorityQueue(nodeLists);
        System.out.print("|");
        while (mergeKList != null) {
            System.out.print(mergeKList.val + "|");
            mergeKList = mergeKList.next;
        }
    }

    public static ListNode mergeKListsUsePriorityQueue(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));

        for (ListNode list : lists) {
            if (list != null) {
                pq.offer(list);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode currNode = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            currNode.next = node;
            currNode = node;
            if (node.next != null) {
                pq.offer(node.next);
            }
            node.next = null;
        }

        return dummy.next;
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode headNode = null;
        int headIdx = -1;
        for (int i = 0; i < lists.length; i++) {
            ListNode currNode = lists[i];
            if (currNode != null && headNode == null) {
                headNode = currNode;
                headIdx = i;
                continue;
            }
            if (currNode != null && currNode.val < headNode.val) {
                headNode = currNode;
                headIdx = i;
            }
        }
        if (headNode == null) {
            return null;
        }

        lists[headIdx] = lists[headIdx].next;
        headNode.next = null;
        ListNode tailNode = headNode;

        while (true) {
            ListNode nextNode = null;
            int nextIdx = -1;
            for (int i = 0; i < lists.length; i++) {
                ListNode currNode = lists[i];
                if (currNode != null && nextNode == null) {
                    nextNode = currNode;
                    nextIdx = i;
                    continue;
                }
                if (currNode != null && currNode.val < nextNode.val) {
                    nextNode = currNode;
                    nextIdx = i;
                }
            }
            if (nextNode == null) {
                break;
            }
            lists[nextIdx] = lists[nextIdx].next;
            tailNode.next = nextNode;
            tailNode = nextNode;
            tailNode.next = null;
        }
        return headNode;
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

