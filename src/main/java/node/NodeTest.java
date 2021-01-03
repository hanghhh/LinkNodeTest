package node;

public class NodeTest {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode b = listNode;
        listNode.value = 2;
        System.out.println(b.value);

    }

    /**
     * 判断链表是否存在环，存在环返回首个环节点，不存在返回null
     * 思路：
     * 快慢指针，相交后快指针回到head，再次相交即使环点
     */
    public ListNode getloopNode(ListNode head) {
        //只要出现null，代表没有环，也保证快指针初始化时不会产生空指针
        if(head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode s = head.next;
        ListNode f = head.next.next;
        while (s != f) {
            if(s.next == null || f.next.next == null) {
                return null;
            }
            s = s.next;
            f = f.next;
        }
        //跳出循环时指针相遇，快指针回到初始位置。步长调整为1
        f = head;
        while (s != f) {
            s = s.next;
            f = f.next;
        }
        return s;
    }

    /**
     * 相交链表
     * 思路
     * 1.不考虑空间复杂度使用hash
     * 2.获取链表长度差值，
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }
        //如果两个链表尾部节点相同，则必然是某处相交
        ListNode curA = headA; //代表较长得数组
        ListNode curB = headB;
        int lengthA = 0; //链表长度差值
        int lengthB = 0; //链表长度差值
        //获取A链表尾部节点
        while (curA.next != null) {
            lengthA++;
            curA = curA.next;
        }
        //获取B链表尾部节点
        while (curB.next != null) {
            lengthB++;
            curB = curB.next;
        }
        if(curA != curB) {
            return null;
        }

        //n得正负代表数组长度比较结果
        int n = lengthA -lengthB;
        curA = n > 0 ? headA : headB; //长数组头部
        curB = curA == headA ? headB : headA;
        n = Math.abs(n);
        while (n != 0) {
            n--;
            curA = curA.next;
        }
        while (curA != curB) {
            curA = curA.next;
            curB = curB.next;
        }
        return curA;
    }

    /**
     * 删除链表倒数第N个节点
     * 思路：快慢指针，快指针先走n步，双指针步长为一同时走，快指针到达尾部时慢指针为倒数第n个节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode f = head;
        ListNode s = head;
        while (n !=0) {
            n--;
            f = f.next;
        }
        ListNode sPre = head;

        while (f != null) {
            sPre = s;
            f = f.next;
            s = s.next;
        }
        //慢指针没有移动，只有一个头或者两个节点删除倒数第二个
        if(s == head) {
            return head.next;
        }
        sPre.next = s.next;
        return head;

    }


}
