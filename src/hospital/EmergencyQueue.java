package hospital;

/**
 * A FIFO queue of patients waiting for emergency treatment.
 * Implemented manually using a singly linked list (front + rear pointers)
 * rather than java.util.Queue, since this is the data structure being assessed.
 *
 * Enqueue -> add to the rear (back of the line)
 * Dequeue -> remove from the front (next patient to be treated)
 */
public class EmergencyQueue {
    private QueueNode front;
    private QueueNode rear;
    private int size;

    public EmergencyQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    // ---------- ENQUEUE ----------

    public void enqueue(EmergencyPatient patient) {
        QueueNode newNode = new QueueNode(patient);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Added to emergency queue: " + patient);
    }

    // ---------- DEQUEUE ----------

    public EmergencyPatient dequeue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patient to call in for treatment.");
            return null;
        }
        EmergencyPatient removed = front.data;
        front = front.next;
        if (front == null) {
            rear = null; // queue is now empty
        }
        size--;
        return removed;
    }

    // ---------- DISPLAY ----------

    public void display() {
        if (isEmpty()) {
            System.out.println("No patients currently waiting in the emergency queue.");
            return;
        }
        System.out.println("---- Patients waiting (front -> rear) ----");
        QueueNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.data);
            current = current.next;
            position++;
        }
    }
}
