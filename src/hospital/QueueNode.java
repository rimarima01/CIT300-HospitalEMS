package hospital;

/**
 * A single node used internally by EmergencyQueue.
 * The queue is implemented as a singly linked list with head (front) and tail (rear)
 * pointers so enqueue and dequeue both run in O(1) time.
 */
public class QueueNode {
    EmergencyPatient data;
    QueueNode next;

    public QueueNode(EmergencyPatient data) {
        this.data = data;
        this.next = null;
    }
}
