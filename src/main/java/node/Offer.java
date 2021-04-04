package node;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Offer {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head4 = new ListNode(3);
        head.next = head2;
        head2.next = head4;
        int[] ints = reversePrint(head);
        String s = Arrays.toString(ints);
        System.out.println("s = " + s);

    }


    public static int[] reversePrint(ListNode head) {
        if(head == null) {
            return new int[]{};
        }
        Stack<ListNode> stack = new Stack<ListNode>();
        while(head != null) {
            stack.push(head);
            head = head.next;
        }
        int size = stack.size();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = stack.pop().value;
        }
        return arr;
    }


}

/**
 * 首先明确，前序遍历，每个子树也是前序遍历，中序遍历得每个子树也是中序遍历
 * 找到根节点，中序遍历能划分出左右子树。分治思想
 */
class Solution {
    int[] pre; // 前序遍历啊得bao
    HashMap<Integer, Integer> inMap = new HashMap();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        pre = preorder;
        int rootPre = 0;//树根节点位于前序遍历得第一个
        int inLeft = 0;//左数得开始边界是中序遍历得0索引
        int inRight = inorder.length -1;//右树得结束边界是中序遍历得结尾索引
        //传入三个参数，这个方法的含义就是在既定得数组范围内确定一个树得头（子树），
        return build(rootPre, inLeft, inRight);
    }

    private TreeNode build(int rootPre, int inLeft, int inRight) {
        //左边界和右边界相等时，说明传入得子树只有一个节点了，把他设置成这个子树得头就可以返回了，相等得时候应该是最后一次有效操作
        if(inLeft>inRight){
            return null;
        }
        //设置头节点信息，第一次设置的是整棵树得头节点，之后为各个子树得头节点。
        int val = pre[rootPre];
        TreeNode root = new TreeNode(val);
        //根据头节点得值获取到了在中序遍历中根节点得位置，这时，中序便利的 左侧为左子树，右侧为右子树，将左子树和右子树分别作为递归资源传递
        Integer rootIndexOnIn = inMap.get(val);
        //开始递归处理左子树，这时候已经确定好根节点了，则左子树根节点得位置由前序遍历性质可得，就在前序遍历数组得下一个位置   rootPre + 1
        //左子树子树组得左侧起始点为中序遍历得最左侧，右侧为根节点索引得前一个 inLeft, rootIndexOnIn - 1
        root.left = build(rootPre + 1,inLeft, rootIndexOnIn - 1);
        //递归处理右子树，右子树得根节点应当是 ，前序遍历 去除当前根节点，去除左子树长度，右子树开始的第一个位置，
        //右子树的左边边界是当前根节点得下一个节点，右边界是中序遍历得右侧。

        //rootPre + (rootIndexOnIn -inLeft)
        // rootIndexOnIn - inLeft  根节点位置减去左边界就是左子树啊长度
        // 根节点位置 + 左子树长度 得下一个位置，就是右子树得根节点
        root.right = build(rootPre + (rootIndexOnIn -inLeft) + 1, rootIndexOnIn + 1, inRight);
        return root;
    }
}
