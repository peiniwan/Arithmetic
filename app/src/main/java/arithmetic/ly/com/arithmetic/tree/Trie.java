package arithmetic.ly.com.arithmetic.tree;

/**
 * 字典树
 * 一种专门处理字符串匹配的数据结构，用来解决在一组字符串集合中快速查找某个字符串的问题.
 * 如果要在一组字符串中，频繁地查询某些字符串，用 Trie 树会非常高效。
 * 构建 Trie 树的过程，需要扫描所有的字符串，时间复杂度是 O(n)（n 表示所有字符串的长度和）。
 * 但是一旦构建成功之后，后续的查询操作会非常高效。
 * 构建好 Trie 树后，在其中查找字符串的时间复杂度是 O(k)，k 表示要查找的字符串的长度。
 */
public class Trie {
    public class TrieNode {
        public char data;
        public TrieNode[] children = new TrieNode[26];
        public boolean isEndingChar = false;
        public TrieNode(char data) {
            this.data = data;
        }
    }

    private TrieNode root = new TrieNode('/'); // 存储无意义字符,root不存储字符

    // 往Trie树中插入一个字符串
    public void insert(String s) {
        char[] text =s.toCharArray();
        TrieNode p = root;
        for (int i = 0; i < text.length; ++i) {
            int index = text[i] - 'a';//65～90为26个大写英文字母，97～122号为26个小写英文字母
            if (p.children[index] == null) {
                TrieNode newNode = new TrieNode(text[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    // 在Trie树中查找一个字符串
    public boolean search(String s) {
        char[] pattern =s.toCharArray();

        TrieNode p = root;
        for (int i = 0; i < pattern.length; ++i) {
            int index = pattern[i] - 'a';
            if (p.children[index] == null) {
                return false; // 不存在pattern
            }
            p = p.children[index];
        }
        if (p.isEndingChar == false) return false; // 不能完全匹配，只是前缀
        else return true; // 找到pattern
    }

    public boolean startsWith(String s) {
        char[] pattern =s.toCharArray();

        TrieNode p = root;
        for (int i = 0; i < pattern.length; ++i) {
            int index = pattern[i] - 'a';
            if (p.children[index] == null) {
                return false; // 不存在pattern
            }
            p = p.children[index];
        }
        return true;
    }




}