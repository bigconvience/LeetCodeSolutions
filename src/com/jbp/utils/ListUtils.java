package com.jbp.utils;


import java.util.List;

public class ListUtils {
    public static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + " : ");
            cur = cur.next;
        }
        System.out.println(":end");
    }

    public static void printListList(List<List> list) {
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            printList(list.get(i));
        }
        System.out.println("]");
    }
    public static void printList(List list) {
        System.out.print("[");
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            System.out.print(list.get(i));
            if (i < listSize - 1) {
                System.out.print(',');
            }
        }
        System.out.print("]");
    }
}
