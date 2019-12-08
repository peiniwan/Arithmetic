package arithmetic.ly.com.arithmetic.linkedlist;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class LinkedUtils {

    public static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
        }

        static void printLinkedList(ListNode head) {
            while (head != null) {
                System.out.print(head.val);
                System.out.print(" ");
                head = head.next;
            }
            System.out.println();
        }
    }


    public ListNode createLinkedList(List<Integer> data) {
        if (data.isEmpty()) {
            return null;
        }
        ListNode firstNode = new ListNode(data.get(0));
        firstNode.next = createLinkedList(data.subList(1, data.size()));
        return firstNode;

    }


    /**
     * 反转链表，可以用栈,递归本质就是一个栈，理解递归
     */
    public ListNode printListFromTailToHead(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode pre = null;
        while (head.next != null) {
            stack.push(head);
            head = head.next;
        }
        pre = head;
        while (!stack.isEmpty()) {
            head.next = stack.pop();
            head = head.next;
        }
        head.next = null;
        return pre;
    }


    /**
     * 循环反转链表
     * 利用三个指针把链表反转，关键是 r 指针保存断开的节点。
     */
    public ListNode reverseLink(ListNode head) {
        ListNode reverse = null;  //用一个新的空链表存放反转后的链表
        while (head != null) {  //当原链表的节点没有被剥离完时不断循环
            ListNode second = head.next;    //初始化原链表首节点的下一个节点
            head.next = reverse;  //head.next当作是个变量，原链表首节点的下一个节点链接到新链表的首节点处
            reverse = head;     //下一跳节点链接完成后，将原链表首节点放入到新链表中，成为新链表的首节点
            head = second;   //从原链表中剥离掉原首节点，原链表首节点的下一个节点成为新的原链表首节点，用于下一次循环
        }
        return reverse;
        // 出来后 head=second=2(3)           reverse=1(null)
        //       head=second=3(null)     reverse =2(1)
        //       head=null(null)        reverse =3(2)1
    }

    /**
     * 递归反转链表(打印)
     */
    public ListNode reverseLinkedList(ListNode head) {
        // size == 0 or size == 1
        if (head == null || head.next == null) {
            //第一次出来的是3,他的nextnull
            return head;
        }
        //先一般再特殊，先假设可以反转，缩小问题规模程度1
        //写完一般后再一行一行看可能会出现什么问题
        //递归执行完后，递归上面的方法就不回执行了
        //2-1-null
        ListNode newHead = reverseLinkedList(head.next);
        head.next.next = head;//2->1
        head.next = null;//1->null
//      head是2，第一次出来3的next是2，2的next是null
        return newHead;
    }

    //==================== 增删改查 ==================

    /**
     * 插入头节点
     */
    public ListNode addFirstNode(ListNode head, int data) {
        ListNode node = new ListNode(data);
        node.next = head;
        return node;
    }

    /**
     * 插入尾节点
     * （重要）找到未节点，注意这里是当元素的下一个元素为空的时候这个节点即为未节点
     */
    public ListNode addLastNode(ListNode head, int data) {
        ListNode node = new ListNode(data);
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = node;
        return head;
    }

    /**
     * 在任意位置插入节点 在index的后面插入
     */
    public ListNode add(ListNode head, int index, int data) {
        int pos = 0;
        ListNode node = new ListNode(data);
        ListNode current = head;
        ListNode previous = head;
        while (pos != index) {
            previous = current;
            current = current.next;
            pos++;
        }
        node.next = current;//最后current 1 null  previous 2  1
        previous.next = node;// node.next设为index后面的节点，index.next设为node
        pos = 0;
        return head;
    }


    /**
     * 删除一个头结点
     */
    public ListNode deleteFirstNode(ListNode head) {
        return head.next;
    }

    /**
     * 删除链表的下一个节点
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }


    /**
     * 循环删除链表某个节点值
     */
    public ListNode removeNode(ListNode head, int value) {
        if (head == null) {
            return null;
        }
        while (head != null && head.val == value) {
            head = head.next;
        }
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == value) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    /**
     * O(1)时间删除链表倒数第k个节点,看下面
     * 长度-k=要删的位置
     */
    public ListNode findKthToTail(ListNode head, int k) {
        if (head == null) return head;
        ListNode current = head;  //作为遍历链表的游标
        int num = 0;  //用于计数链表最后一位的索引，即链表项数量减1
        while (current != null) {
            current = current.next;
            num++;
        }
        if (num < k) return null;  //如果倒数第k个节点的索引在整个链表之外，返回空
//        ListNode current2 = head;  //前面的current已经指向链表尾部，再用一个新的游标遍历链表
//        for (int i = 0; i < num - k; i++) {  //遍历num-k-1次后，到达倒数第k个节点
//            current2 = current2.next;
//        }
        ListNode pre = head;
        ListNode cur = head;
        int pos = 0;
        while (pos != num - k) {
            pre = cur;
            cur = cur.next;
            pos++;
        }
        pre.next = cur.next;//pre.next和cur.val就是要删除的值
        return head;  //现在第二个游标指向倒数第k个节点，直接返回
    }

    /**
     * 删除(输出)链表的倒数第k个节点
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 快指针先移n个节点，接下来,快慢指针一起移动,两指针之间一直保持n个节点,当快指针到链表底了,操作慢指针,删除要删除的元素!
     */
    public ListNode removeNthFromEnd(ListNode head, int k) {
        if (head == null)
            return null;
        if (k == 0) {
            System.out.println("k应该从1开始");
            return null;
        }
        ListNode slowNode = head;
        ListNode fastNode = head;

        for (int i = 0; i < k; i++) {
            if (fastNode.next == null) {
                System.out.println("k不应该大于链表长度");
                return null;
            }
            fastNode = fastNode.next;
        }
        while (fastNode.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next;
        }
        slowNode.next = slowNode.next.next;//slowNode.next就是要删的
        return head;
    }

    /**
     * 根据位置查找节点信息
     */
    public int findByPos(ListNode head, int index) {
        int pos = 0;
        ListNode current = head;
        while (pos != index) {
            if (current.next == null) {
                return -1;
            }
            current = current.next;
            pos++;
        }
        return current.val;
    }

    /**
     * 根据数据查找节点信息
     */
    public int findByData(ListNode head, int data) {
        int pos = 0;
        ListNode current = head;
        while (current.val != data) {
            if (current.next == null) {
                return -1;
            }
            current = current.next;
            pos++;
        }
        return pos;
    }


    /**
     * 环形链表
     * 我们遍历所有结点并在哈希表中存储每个结点的引用（或内存地址）。如果当前结点为空结点 null（即已检测到链表尾部的下一个结点），
     * 那么我们已经遍历完整个链表，并且该链表不是环形链表。如果当前结点的引用已经存在于哈希表中，那么返回 true（即该链表为环形链表）。
     */
    public boolean hasCycle(ListNode head) {
        Set<ListNode> nodesSeen = new HashSet<>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                return true;
            } else {
                nodesSeen.add(head);
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 慢指针每次移动一步，而快指针每次移动两步。
     * 如果列表中不存在环，最终快指针将会最先到达尾部，此时我们可以返回 false。
     * 现在考虑一个环形链表，把慢指针和快指针想象成两个在环形赛道上跑步的运动员（分别称之为慢跑者与快跑者）。而快跑者最终一定会追上慢跑者。
     * 这是为什么呢？考虑下面这种情况（记作情况 A）- 假如快跑者只落后慢跑者一步，在下一次迭代中，它们就会分别跑了一步或两步并相遇。
     * 其他情况又会怎样呢？例如，我们没有考虑快跑者在慢跑者之后两步或三步的情况。但其实不难想到，因为在下一次或者下下次迭代后，
     * 又会变成上面提到的情况 A。
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }


    /**
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;//注意鲁棒性
        if (list2 == null) return list1;
        if (list1.val <= list2.val) {  //利用归并排序的递归思想，将两个链表的较小节点链接起来
            list1.next = Merge(list1.next, list2);  //如果list1当前节点小于list2当前节点，链表放入较小节点并将索引往后一个节点，与list2的原较大节点继续比较
            return list1;
        } else {
            list2.next = Merge(list2.next, list1);  //如果list2当前节点小于list1当前节点，链表放入较小节点并将索引往后一个节点，与list1的原较大节点继续比较
            return list2;
        }
    }

    /**
     * 37.两个链表的第一个公共节点(公共节点就考虑set)
     * 空间复杂度（m+n）时间复杂度（m+n）
     */
    public ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode current1 = pHead1;  //设置两个遍历两个链表的游标，初始位置在两个链表首节点
        ListNode current2 = pHead2;
        HashSet<ListNode> set = new HashSet<ListNode>();  //利用HashSet存放遍历过的第一条链表
        while (current1 != null) {
            set.add(current1);
            current1 = current1.next;
        }
        while (current2 != null) {
            if (set.contains(current2))
                return current2;  //如果HashSet中存有和current2游标节点相同的第一条链表中的节点，则找到公共节点
            current2 = current2.next;
        }
        return null;  //如果没找到公共节点，或公共节点为空，返回null
    }

    /**
     * 37.两个链表的第一个公共节点(2)
     * 长链表先走n步，第一个相同的就是公共节点
     * 时间复杂度（m+n）空间复杂度（不需要栈了，小）
     */
    public static ListNode findFirstCommonNode2(ListNode head1, ListNode head2) {
        int len1 = getListLength(head1);
        int len2 = getListLength(head2);
        ListNode longListNode = null;
        ListNode shortListNode = null;
        int dif = 0;
        if (len1 > len2) {
            longListNode = head1;
            shortListNode = head2;
            dif = len1 - len2;
        } else {
            longListNode = head2;
            shortListNode = head1;
            dif = len2 - len1;
        }
        for (int i = 0; i < dif; i++) {
            longListNode = longListNode.next;
        }
        while (longListNode != null && shortListNode != null
                && longListNode != shortListNode) {
            longListNode = longListNode.next;
            shortListNode = shortListNode.next;
        }
        return longListNode;
    }

    private static int getListLength(ListNode head1) {
        int result = 0;
        if (head1 == null)
            return result;
        ListNode point = head1;
        while (point != null) {
            point = point.next;
            result++;
        }
        return result;
    }


    public static void main(String[] args) {
        LinkedUtils creator = new LinkedUtils();

//        ListNode.printLinkedList(creator.reverseLinkedList(
//                creator.createLinkedList(Arrays.asList(1, 2, 3))));

//        ListNode.printLinkedList(creator.reverseLoopLinkedList(
//                creator.createLinkedList(Arrays.asList(1, 2, 3))));

//        ListNode.printLinkedList(creator.removeNthFromEnd(
//                creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5)), 2));

//        ListNode.printLinkedList(creator.removeNode(
//                creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5)), 2));

        ListNode.printLinkedList(creator.findKthToTail(
                creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5)), 3));

//        ListNode.printLinkedList(creator.reverseLink(
//                creator.createLinkedList(Arrays.asList(1, 2, 3))));

    }


}
