package hospital;

/**
 * A LIFO stack of completed treatment records.
 * Implemented manually using a singly linked list where the head of the
 * list is the top of the stack, giving O(1) push and pop.
 *
 * Push -> add a newly completed treatment on top
 * Pop  -> remove the most recently completed treatment
 */
public class TreatmentStack {
    private StackNode top;
    private int size;

    public TreatmentStack() {
        top = null;
        size = 0;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    // ---------- PUSH ----------

    public void push(TreatmentRecord record) {
        StackNode newNode = new StackNode(record);
        newNode.next = top;
        top = newNode;
        size++;
        System.out.println("Treatment record pushed to history: " + record);
    }

    // ---------- POP ----------

    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment history is empty. Nothing to pop.");
            return null;
        }
        TreatmentRecord removed = top.data;
        top = top.next;
        size--;
        return removed;
    }

    // ---------- PEEK (bonus helper, not required but useful) ----------

    public TreatmentRecord peek() {
        if (isEmpty()) {
            return null;
        }
        return top.data;
    }

    // ---------- DISPLAY ----------

    public void display() {
        if (isEmpty()) {
            System.out.println("No treatment records in history.");
            return;
        }
        System.out.println("---- Treatment history (most recent first) ----");
        StackNode current = top;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.data);
            current = current.next;
            position++;
        }
    }
}
