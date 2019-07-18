package arithmetic.ly.com.arithmetic.other;

import java.util.ArrayList;

public class ToOffer {

    public static void main(String[] args) {
        ToOffer toOffer = new ToOffer();
        toOffer.replaceSpace("We Are Happy");
    }

    /**
     * 替换空格
     * 从前往后就把后面的覆盖了
     */
    public String replaceSpace(String str) {
        StringBuffer res = new StringBuffer();
        int len = str.length() - 1;
        for (int i = len; i >= 0; i--) {
            if (str.charAt(i) == ' ')
                res.append("02%");
            else
                res.append(str.charAt(i));
        }
        System.out.println(res.reverse().toString());
        return res.reverse().toString();
    }


    /**
     * 从尾到头打印链表
     * 一种方法是利用栈来实现；
     * 另外一种方法是利用三个指针把链表反转，关键是 r 指针保存断开的节点。
     */
    public ArrayList<Integer> printListFromTailToHead(LeetCode.ListNode listNode) {
        if (listNode == null)
            return new ArrayList<Integer>();
        LeetCode.ListNode head = listNode;//倒序后存在这了
        LeetCode.ListNode cur = listNode.next;
        while (cur != null) {
            LeetCode.ListNode temp = cur.next;
            cur.next = head;
            head = cur;
            cur = temp;
        }
        //此时listNode的next还指向第二个node，所以要让listNode.next=null,防止循环
        listNode.next = null;
        ArrayList<Integer> res = new ArrayList<Integer>();
        while (head != null) {
            res.add(head.val);
            head = head.next;
        }
        return res;
    }
}
