package hospital;

/**
 * A singly linked list holding one patient's previous hospital visits,
 * ordered from oldest (head) to most recently added (tail).
 *
 * Supported operations:
 *  - addVisit(Visit)          : append a new visit at the end - O(n) (O(1) if a tail pointer is kept)
 *  - removeVisit(int visitId) : remove a visit by its ID - O(n)
 *  - searchVisit(int visitId) : find a visit by its ID - O(n)
 *  - display()                : print all visits in order - O(n)
 */
public class VisitHistory {
    private VisitNode head;
    private VisitNode tail; // kept for O(1) appends
    private int size;

    public VisitHistory() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    // ---------- ADD ----------

    public void addVisit(Visit visit) {
        VisitNode newNode = new VisitNode(visit);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // ---------- REMOVE ----------

    public boolean removeVisit(int visitId) {
        if (isEmpty()) {
            return false;
        }

        // Special case: removing the head node
        if (head.data.getVisitId() == visitId) {
            head = head.next;
            if (head == null) {
                tail = null; // list is now empty
            }
            size--;
            return true;
        }

        VisitNode current = head;
        while (current.next != null) {
            if (current.next.data.getVisitId() == visitId) {
                VisitNode toRemove = current.next;
                current.next = toRemove.next;
                if (toRemove == tail) {
                    tail = current; // removed node was the tail, update it
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false; // visit ID not found
    }

    // ---------- SEARCH ----------

    public Visit searchVisit(int visitId) {
        VisitNode current = head;
        while (current != null) {
            if (current.data.getVisitId() == visitId) {
                return current.data;
            }
            current = current.next;
        }
        return null; // not found
    }

    // ---------- DISPLAY ----------

    public void display() {
        if (isEmpty()) {
            System.out.println("This patient has no recorded visit history.");
            return;
        }
        VisitNode current = head;
        while (current != null) {
            System.out.println("  - " + current.data);
            current = current.next;
        }
    }
}
