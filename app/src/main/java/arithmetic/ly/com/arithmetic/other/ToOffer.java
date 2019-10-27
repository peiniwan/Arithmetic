package arithmetic.ly.com.arithmetic.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class ToOffer {

    public static void main(String[] args) {
        ToOffer toOffer = new ToOffer();
        toOffer.replaceSpace("We Are Happy");
    }

    /**
     * 2.替换空格
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
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     */
    class TwoStacksAsQueue {
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();

        public void push(int node) {
            stack1.push(node);
        }

        public int pop() {
            if (stack1.isEmpty() && stack2.isEmpty()) {
                throw new RuntimeException("The queue is empty.");
            }
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
    }


    /**
     * 12.数值的整数次方
     */
    public double Power(double base, int exponent) {
        int n = Math.abs(exponent);
        if (n == 0) return 1;
        if (n == 1) return base;
        //递归：若n为偶数，则a^n=a^(n/2)*a^(n/2)；若n为奇数，则a^n=(a^(n-1)/2)*(a^((n-1)/2))*a，时间复杂度为O(log(n))
        double result = Power(base, n >> 1);  //将n的二进制右移一位，意味着除以2，求得a^(n/2)或a^((n-1)/2)
        result *= result;  //将a^(n/2)或a^((n-1)/2)进行平方，变为a^n或a^(n-1)
        if ((n & 1) == 1) result *= base;  //在n的二进制右移递归后，若n为奇数，二进制最低位一定是1，这样就再乘一次a。偶数二进制的最低位为0
        if (exponent < 0) result = 1 / result;  //若指数为负数，则求倒数
        return result;
    }


    /**
     * 13.输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
     * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     */
    private static void mysort(int[] array) {
        if (array == null) {
            return;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            while (left < right && !isEven(array[left])) {
                left++;
            }
            while (left < right && isEven(array[right])) {
                right--;
            }
            if (left < right) {
                int temp = array[right];
                array[right] = array[left];
                array[left] = temp;
            }
            if (left >= right) {//如果第二个指针已到了第一个指针前面，表明所有的奇数都位于偶数的前面
                break;
            }
        }
    }

    //方便扩展
    private static boolean isEven(int i) {
        return (i & 0x1) == 0;
    }


    /**
     * 20.定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
     */
    Stack<Integer> data = new Stack<Integer>();  //初始化一个数据栈来存放所有元素
    Stack<Integer> min = new Stack<Integer>();  //初始化一个辅助栈存放依次存放最小元素
    Integer temp = null;  //不能用int，只有Integer对象才能为空

    public void push(int node) {
        if (temp != null) {
            if (node <= temp) {  //当有比辅助栈min中的栈顶元素更小或等于的元素时，将更小元素放入辅助栈以及数据栈，否则只放入数据栈
                temp = node;
                min.push(node);
            }
            data.push(node);
        } else {  //当一开始temp值为空时，第一个元素可以放入最小栈和数据栈中
            temp = node;
            min.push(node);
            data.push(node);
        }
    }

    public void pop() {
        int dataNumber = data.pop();
        int minNumber = min.pop();
        if (dataNumber != minNumber) min.push(minNumber);  //如果数据栈中出栈的元素并非最小元素，再把最小元素放回辅助栈
    }

    public int top() {
        return data.peek();  //检查数据栈中的栈顶元素但不出栈
    }

    public int min() {
        return min.peek();  //检查辅助栈中的栈顶元素但不出栈，即最小元素
    }


    /**
     * 28.输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
     * 输入描述：输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
     * 原理图：https://uploadfiles.nowcoder.net/images/20170705/7578108_1499250116235_8F032F665EBB2978C26C4051D5B89E90
     */
    public ArrayList<String> permutation(String str) {
        ArrayList<String> result = new ArrayList<String>();  //存放字母按各种排列情况的字符串
        if (str != null && str.length() > 0) {
            permutationHelper(str.toCharArray(), 0, result);
            Collections.sort(result);  //对各种排列情况输出的字符串按照字典序排序
        }
        return result;
    }

    public void permutationHelper(char[] cs, int i, ArrayList<String> list) {
        if (i == cs.length - 1) {  //当交换索引到达字符数组最后一位，相当于字符间的交换已经完成到了字符串倒数第二位，一种情况已经交换完成，输出一种字符组合结果
            String val = String.valueOf(cs);
            if (!list.contains(val)) {
                list.add(val);
            }
        } else {
            for (int j = i; j < cs.length; j++) {
                swap(cs, i, j);  //交换当前层的元素
                permutationHelper(cs, i + 1, list);  //在上一行已交换一次的基础上再对已交换元素的后面元素进行交换
                swap(cs, i, j);  //负负得正，将交换过的元素再交换回到原来的样子，相当于回退到之前的状态，避免已改变值的字符数组影响其他不同情况下的交换
            }
        }
    }

    public void swap(char[] cs, int i, int j) {
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }


    /*
     * 29.数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
     * */
    public int moreThanHalfNum(int[] array) {
        //数组为空与数组长度为0概念不同，先判断是否为空是因为如果对空数组求长度会报NullPointerException异常
        if (array == null || array.length == 0) return 0;
        int result = array[0];  //先将数组开头元素作为向后遍历的游标，就像士兵阵地攻守，一开始第一个数字为第一个士兵，计数为1
        int times = 1;  //初始出现次数设为1
        for (int i = 1; i < array.length; i++) {
            if (times == 0) {  //计数为0时说明阵地上没有士兵，以新的数组当前元素值作为新的士兵，计数为1进行后续比较
                result = array[i];
                times = 1;
            } else {
                if (array[i] == result) times++;  //当阵地上有士兵时，如果遇到相同值为友军，计数加1
                else times--;  //遇到不相同元素为敌人，同归于尽计数减1
            }
        }
        times = 0;  //之前的比较后阵地上最后留下来的士兵一定是出现次数最多的，将计数重置，从数组开头重新计算出现次数
        for (int i = 0; i < array.length; i++) {
            if (array[i] == result) times++;
        }
        if (2 * times <= array.length) return 0;  //如果出现次数没超过数组一半长度，则返回0，否则返回该元素
        else return result;
    }

    /*
     * 30.输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
     * */
    public ArrayList<Integer> getLeastNumbers(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (input == null || input.length == 0 || k > input.length) return list;
        Arrays.sort(input);
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }


    //======================================= 树 =======================================
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }


    /*
     * 17.输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
     * */
    public boolean hasSubtree(TreeNode root1, TreeNode root2) {
        boolean result = false;  //设置匹配标志位，匹配成功设为true
        if (root1 != null && root2 != null) {  //仅当root1和root2不为空树时比较，否则直接返回false
            if (root1.val == root2.val) {  //当二叉树root1和root2的根节点值相同时，比较它们的子树是否完全一致
                result = doesTree1HasTree2(root1, root2);
            }
            if (!result) {  //如果在root1的根节点处没有找到和root2根节点的值相同的节点，递归查找root1的左子树里是否有和root2根节点的值相同的节点
                result = hasSubtree(root1.left, root2);
            }
            if (!result) {  //如果在root1的左子树中没有找到和root2根节点的值相同的节点，递归查找root1的右子树里是否有和root2根节点的值相同的节点
                result = hasSubtree(root1.right, root2);
            }
        }
        return result;
    }

    public boolean doesTree1HasTree2(TreeNode root1, TreeNode root2) {
        if (root2 == null) {  //如果二叉树root2已经被遍历完，此时root1不管有没有遍历完，都说明匹配，二叉树root2是root1的子树
            return true;
        }
        if (root1 == null && root2 != null) {  //如果二叉树root1已经被遍历完，而root2没有被遍历完，说明root1里不包含root2
            return false;
        }
        if (root1.val != root2.val) {  //在递归遍历的比较过程中，也许root1中某个子树前面一些节点与root2的前面一些节点一致，但只要有中间一个节点的值不一致，说明root2不是root1的子树
            return false;
        }
        return doesTree1HasTree2(root1.left, root2.left) && doesTree1HasTree2(root1.right, root2.right);  //在root1当前子树根节点与root2根节点值相同时，递归比较它们的左子树和右子树是否都一致
    }

    /*
     * 18.操作给定的二叉树，将其变换为原二叉树的镜像。（画图形象化）
     * 二叉树的镜像定义：源二叉树
    	    8
    	   /  \
    	  6   10
    	 / \  / \
    	5  7 9 11
    	镜像二叉树
    	    8
    	   /  \
    	  10   6
    	 / \  / \
    	11 9 7  5
     * */
    public void Mirror(TreeNode root) {//前序遍历
        if (root != null) { //当前树根节点不为空时，交换自己的左右子树根节点
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            Mirror(root.left);  //递归交换左子树的左右子树根节点
            Mirror(root.right);  //递归交换右子树的左右子树根节点
        }
    }


    /*
     * 22.从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     *
     * */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<TreeNode> queue = new ArrayList<TreeNode>();  //模拟一个队列来存放相应的二叉树节点
        if (root == null) return list;
        queue.add(root);
        while (queue.size() != 0) {  //当“队列中”有二叉树节点时不断循环
            //从“队列”中移除二叉树当前根节点，并赋给一个临时二叉树变量，按照下面queue添加元素的顺序一定是先添加左节点再添加右节点，所以移除顺序也一定能是前序遍历
            TreeNode temp = queue.remove(0);
            if (temp.left != null) {  //如果有左子树就将左节点添加到“队列”中
                queue.add(temp.left);
            }
            if (temp.right != null) {  //如果有右子树就将右节点添加到“队列”中
                queue.add(temp.right);
            }
            list.add(temp.val);  //将二叉树当前根节点的值添加到列表中
        }
        return list;
    }

    /*
     * 24.输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
     *  int[] array = {5, 7, 6, 9, 11, 10, 8};
        int[] array={7,4,6,5};
     * */
    public boolean verifySquenceOfBST(int[] sequence) {
        if (sequence.length == 0) return false;
        if (sequence.length == 1) return true;
        return isPostorderTraversal(sequence, 0, sequence.length - 1);
    }

    private boolean isPostorderTraversal(int[] a, int start, int end) {
        if (start >= end) return true;  //如果从底往上递归的过程到最后开始和结束索引相遇，说明已经遍历到根节点，整个数组确实为后序遍历二叉树
        int i = start;  //后序遍历是先左节点，再右节点，最后根节点，因此数组开头元素一定是二叉树左下角最小节点，sequence[end]一定是根节点
        while (a[i] < a[end]) i++;  //从二叉树左下角的最小节点开始与根节点比较，如果左子树所有节点全都小于根节点，索引i会加到根节点左子树右下角的最大节点
        for (int j = i; j < end; j++) {  //在根节点右子树上不断从底部向上与根节点比较，只要有一个右子树节点小于根节点，就一定不是后序遍历二叉树
            if (a[j] < a[end]) return false;
        }
        //递归检查根节点的左子树根节点和右子树根节点下面是否也符合左子树都小于根节点，右子树都大于根节点的特性，如果全都满足则整个数组都是后序遍历二叉树
        return isPostorderTraversal(a, 0, i - 1) && isPostorderTraversal(a, i, end - 1);
    }

    /*
     * 34.丑数
     * 包含的质数因子只有2、3和5的数被称作丑数（Ugly Number）。
     * 例如6、8都是丑数，但14不是，因为它包含质因子7。习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
     * */
    public int getUglyNumber(int index) {
        if (index <= 0) return 0;
        ArrayList<Integer> list = new ArrayList<Integer>();  //用一个列表存放丑数
        int i2 = 0, i3 = 0, i5 = 0;  //一开始丑数为1，因此后面产生的丑数为1*2，1*3，1*5，这里分别存放乘以2的最小的数、乘以3的最小的数、乘以5的最小的数的索引
        list.add(1);
        while (list.size() < index) {
            int m2 = list.get(i2) * 2;
            int m3 = list.get(i3) * 3;
            int m5 = list.get(i5) * 5;
            //比较三个丑数中的最小值，例如第一轮中1*2，1*3和1*5中2最小，则接下来比较2*2，1*3和1*5最小为3，第三轮比较2*2，3*2和1*5，所以先比较后面再比较前面
            int min = Math.min(m2, Math.min(m3, m5));
            list.add(min);  //将比较出的最小丑数放入列表
            if (min == m2)
                i2++;  //如果上一轮比较中的最小丑数来源于三个比较索引中的某一个，则对应索引加一，对应下一个索引的丑数乘2，乘3或乘5后加入下一轮比较
            if (min == m3) i3++;
            if (min == m5) i5++;
        }
        return list.get(list.size() - 1);  //返回列表中最后一个丑数即为从小到大的第N个丑数
    }


    /*
     * 35.第一次只出现一次的字符
     * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）。
     * */
    public int firstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) return -1;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();  //利用一个HashMap散列表存储每个字符出现的次数，字符为键次数为值
        for (int i = 0; i < str.length(); i++) {  //遍历字符串，如果散列表中没有该字符的键就新建一个该字符的键，将值即出现次数设为1，若有说明该字符出现过，将值加一更新出现次数
            if (map.containsKey(str.charAt(i))) {
                int count = map.get(str.charAt(i));
                map.put(str.charAt(i), ++count);  //++在前是因为先将出现次数加1，再更新该字符的出现次数
            } else {
                map.put(str.charAt(i), 1);
            }
        }
        for (int i = 0; i < str.length(); i++) {  //遍历字符串，对每一个当前字符都去查找散列表对应键的值是不是1，找到就返回该字符在字符串中的位置
            if (map.get(str.charAt(i)) == 1) return i;
        }
        return -1;  //若没有找到只出现一次的字符，则返回-1
    }


    /**
     * 38.数字在排序数组中出现的次数
     * 利用二分查找算法（logn）,遍历的话是n
     */
    public int getNumberOfK(int[] array, int k) {
        if (array == null || array.length == 0) return 0;
        //使用二分查找思想，分别查找数字第一次和最后一次出现的位置
        int firstK = getFirstK(array, k, 0, array.length - 1);
        int lastK = getLastK(array, k, 0, array.length - 1);
        if (firstK != -1 && lastK != -1) return lastK - firstK + 1;  //返回重复出现次数
        return 0;  //未找到则返回0
    }

    private int getFirstK(int[] array, int k, int start, int end) {
        if (start > end) return -1;
        int mid = (start + end) >> 1;  //在二进制中按位右移一位相当于除以2
        if (array[mid] > k) {
            return getFirstK(array, k, start, mid - 1);
        } else if (array[mid] < k) {
            return getFirstK(array, k, mid + 1, end);
        } else if (mid - 1 >= 0 && array[mid - 1] == k) {  //如果找到的中点的前一个元素也是该重复元素，说明可能前面还有几个重复元素，则以中点前一位为下一轮终点再查找一次
            return getFirstK(array, k, start, mid - 1);
        } else {
            return mid;  //最终找到的中点即该元素第一次出现的地方
        }
    }

    private int getLastK(int[] array, int k, int start, int end) {
        int mid = (start + end) >> 1;  //在二进制中按位右移一位相当于除以2，在循环外创建变量可避免每次循环都创建一个mid变量，浪费内存
        while (start <= end) {
            if (array[mid] > k) {
                end = mid - 1;
            } else if (array[mid] < k) {
                start = mid + 1;
            } else if (mid + 1 < array.length && array[mid + 1] == k) {  //如果找到的中点的后一个元素也是该重复元素，说明可能后面还有几个重复元素，则以中点后一位为下一轮起点再查找一次
                start = mid + 1;
            } else {
                return mid;  //最终找到的中点即该元素最后一次出现的地方
            }
            mid = (start + end) >> 1;  //每一轮二分查找结束后查找起点或终点都会变化，所以需要更新中点
        }
        return -1;
    }


}
