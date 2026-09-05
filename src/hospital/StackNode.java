package hospital;

/**
 * A single node used internally by TreatmentStack.
 * The stack is implemented as a singly linked list where the head
 * of the list always represents the top of the stack.
 */
public class StackNode {
    TreatmentRecord data;
    StackNode next;

    public StackNode(TreatmentRecord data) {
        this.data = data;
        this.next = null;
    }
}
