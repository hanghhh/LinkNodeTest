package node;

public class NodeTest {
    public static void main(String[] args) {

    }

    /**
     * 判断链表是否存在环，存在环返回首个环节点，不存在返回null
     * 思路：
     * 快慢指针，相交后快指针回到head，再次相交即使环点
     */
    public Node getloopNode(Node head) {
        //只要出现null，代表没有环，也保证快指针初始化时不会产生空指针
        if(head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node s = head.next;
        Node f = head.next.next;
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

}
