package arithmetic.ly.com.arithmetic.linkedlist;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class LinkedUtils {

    // 运行Java文件
    // <option name="delegatedBuild" value="false" />
    ///Users/tal/android/Arithmetic/.idea/gradle.xml
    public static void main(String[] args) {
        LinkedUtils creator = new LinkedUtils();

//        ListNode.printLinkedList(creator.reverseLinkedList(
//                creator.createLinkedList(Arrays.asList(1, 2, 3))));

//        ListNode.printLinkedList(creator.reverseLoopLinkedList(
//                creator.createLinkedList(Arrays.asList(1, 2, 3))));

        //ListNode.printLinkedList(creator.removeNthFromEnd(
        //        creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5)), 2));

//        ListNode.printLinkedList(creator.removeNode(
//                creator.createLinkedList(Arrays.asList(1, 6, 3, 4, 5, 7, 8)), 5));

//        ListNode.printLinkedList(creator.findKthToTail(
//                creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5)), 3));

//        ListNode.printLinkedList(creator.reverseLink(
//                creator.createLinkedList(Arrays.asList(1, 2, 3))));

        ListNode.printLinkedList(creator.reverseList2(
                creator.createLinkedList(Arrays.asList(1, 2, 3,4,5))));


//        creator.findFirstCommonNode2(
//                creator.createLinkedList(Arrays.asList(1, 2, 3)),
//                creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5, 6, 7))
//        );


        //ListNode.printLinkedList(creator.reverse(creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5, 6))));

    }

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
     * 找到未节点，注意这里是当元素的下一个元素为空的时候这个节点即为未节点
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
     * 循环删除链表某个节点值,跟下面类似
     */
    public ListNode removeNode(ListNode head, int value) {
        if (head == null) {
            return null;
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
     * 这样做的目的是创建一个间隔为n的“窗口”。当快指针到达链表末尾时，慢指针正好位于倒数第n个节点。
     * 因为从开始到结束，两者之间始终保持着n个节点的距离。
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
            fastNode = fastNode.next;//3
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
     * https://leetcode-cn.com/problems/linked-list-cycle/solution/141-huan-xing-lian-biao-by-ac_fun-dyet/
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
     * 52.两个链表的第一个公共节点(公共节点就考虑set)
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
     * 52.两个链表的第一个公共节点(2)
     * 长链表先走n步，第一个相同的就是公共节点    如果相同的再前面就找不到了???
     * 时间复杂度（m+n）空间复杂度（不需要栈了，小）
     */
    public ListNode findFirstCommonNode2(ListNode headA, ListNode headB) {
        //统计链表A和链表B的长度
        int lenA = length(headA), lenB = length(headB);

        //如果节点长度不一样，节点多的先走，直到他们的长度一样为止
        while (lenA != lenB) {
            if (lenA > lenB) {
                //如果链表A长，那么链表A先走
                headA = headA.next;
                lenA--;
            } else {
                //如果链表B长，那么链表B先走
                headB = headB.next;
                lenB--;
            }
        }

        //然后开始比较，如果他俩不相等就一直往下走
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        //走到最后，最终会有两种可能，一种是headA为空，
        //也就是说他们俩不相交。还有一种可能就是headA
        //不为空，也就是说headA就是他们的交点
        return headA;
    }

    //统计链表的长度
    private int length(ListNode node) {
        int length = 0;
        while (node != null) {
            node = node.next;
            length++;
        }
        return length;
    }

//=================================== 反转链表 ==================================================

    /**
     * 迭代反转链表
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) //链表为空或者仅1个数直接返回
            return head;
        ListNode p = head, newHead = null;
        while (p != null) {                 //一直迭代到链尾
            ListNode tmp = p.next;          //暂存p下一个地址，防止变化指针指向后找不到后续的数
            p.next = newHead;               //p->next指向前一个空间
            newHead = p;                    //新链表的头移动到p，扩长一步链表
            p = tmp;                        //p指向原始链表p指向的下一个空间
        }
        return newHead;
    }

    /**
     * 递归反转
     */
    public ListNode reverseList2(ListNode h) {
        if (h == null || h.next == null) { //链表为空直接返回，而head.next为空是递归基
            return h;
        }
        ListNode newHead = reverseList2(h.next); //一直循环到链尾
        h.next.next = h;                     //翻转链表的指向
        h.next = null;                          //记得赋值NULL，防止链表错乱，避免两个节点互为next死循环
        return newHead;     //不论当前节点是啥，都返回原本链表的最后一个节点-》也就是反转后的头节点
    }


    /**
     * 反转链表，可以用栈,递归本质就是一个栈，理解递归
     */
    public ListNode printListFromTailToHead(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        while (head.next != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode newNode = head;
        //从栈中弹出每个节点，并将它们添加到新的链表的末尾。
        //我们将当前节点的next字段设置为栈中弹出的节点，然后head = head.next 用于移动到反转列表的下一个节点去继续进行更新。
        while (!stack.isEmpty()) {
            head.next = stack.pop();
            head = head.next;
        }
        head.next = null;
        return newNode;
    }



    ListNode successor = null;

    // 反转以 head 为起点的 n 个节点，返回新的头结点
    ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }

    ListNode reverseBetween(ListNode head, int m, int n) {
        // base case
        if (m == 1) {
            return reverseN(head, n);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }


    // 反转以 a 为头结点的链表
    ListNode reverse3(ListNode a) {
        ListNode pre, cur, nxt;
        pre = null;
        cur = a;
        nxt = a;
        while (cur != null) {
            nxt = cur.next;
            // 逐个结点反转
            cur.next = pre;
            // 更新指针位置
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }

    /**
     * 反转区间 [a, b) 的元素，注意是左闭右开
     */
    ListNode reverse2(ListNode a, ListNode b) {
        ListNode pre, cur, nxt;
        pre = null;
        cur = a;
        nxt = a;
        // while 终止的条件改一下就行了
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }

    /**
     * 如何k个一组反转链表
     * https://labuladong.github.io/algo/%E9%AB%98%E9%A2%91%E9%9D%A2%E8%AF%95%E7%B3%BB%E5%88%97/k%E4%B8%AA%E4%B8%80%E7%BB%84%E5%8F%8D%E8%BD%AC%E9%93%BE%E8%A1%A8.html
     *
     * @param head
     * @param k
     * @return
     */
    ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        // 区间 [a, b) 包含 k 个待反转元素
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            // 不足 k 个，不需要反转，base case
            if (b == null) return head;
            b = b.next;
        }
        // 反转前 k 个元素
        ListNode newHead = reverse2(a, b);
        // 递归反转后续链表并连接起来
        a.next = reverseKGroup(b, k);
        return newHead;
    }


    /* 倒序打印单链表中的元素值 */
    void traverse(ListNode head) {
        if (head == null) return;
        traverse(head.next);
        // 后序遍历代码
//        print(head.val);
    }


    /**
     * 回文链表（简单）
     * https://labuladong.github.io/algo/%E9%AB%98%E9%A2%91%E9%9D%A2%E8%AF%95%E7%B3%BB%E5%88%97/%E5%88%A4%E6%96%AD%E5%9B%9E%E6%96%87%E9%93%BE%E8%A1%A8.html
     */
    // 左侧指针
    ListNode left;

    boolean isPalindrome(ListNode head) {
        left = head;
        return traverse2(head);
    }

    boolean traverse2(ListNode right) {
        if (right == null) return true;
        boolean res = traverse2(right.next);
        // 后序遍历代码
        res = res && (right.val == left.val);
        left = left.next;
        return res;
    }


}
