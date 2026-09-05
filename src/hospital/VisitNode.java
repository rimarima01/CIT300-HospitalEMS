package hospital;

/**
 * A single node in a patient's VisitHistory singly linked list.
 */
public class VisitNode {
    Visit data;
    VisitNode next;

    public VisitNode(Visit data) {
        this.data = data;
        this.next = null;
    }
}
