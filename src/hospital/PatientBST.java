package hospital;

/**
 * Binary Search Tree that stores Patient records keyed by Patient ID.
 *
 * Supported operations:
 *  - insert(Patient)          : O(h) average O(log n)
 *  - search(int patientId)    : O(h)
 *  - delete(int patientId)    : O(h)
 *  - inOrderTraversal()       : O(n), prints patients in ascending Patient ID order
 */
public class PatientBST {
    private PatientNode root;

    public PatientBST() {
        this.root = null;
    }

    // ---------- INSERT ----------

    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private PatientNode insertRec(PatientNode node, Patient patient) {
        if (node == null) {
            return new PatientNode(patient);
        }
        if (patient.getPatientId() < node.patient.getPatientId()) {
            node.left = insertRec(node.left, patient);
        } else if (patient.getPatientId() > node.patient.getPatientId()) {
            node.right = insertRec(node.right, patient);
        } else {
            // Duplicate ID - update the existing record instead of creating a duplicate node
            System.out.println("A patient with ID " + patient.getPatientId() +
                    " already exists. Overwriting record.");
            node.patient = patient;
        }
        return node;
    }

    // ---------- SEARCH ----------

    public Patient search(int patientId) {
        PatientNode result = searchRec(root, patientId);
        return (result == null) ? null : result.patient;
    }

    private PatientNode searchRec(PatientNode node, int patientId) {
        if (node == null || node.patient.getPatientId() == patientId) {
            return node;
        }
        if (patientId < node.patient.getPatientId()) {
            return searchRec(node.left, patientId);
        }
        return searchRec(node.right, patientId);
    }

    // ---------- DELETE ----------

    public boolean delete(int patientId) {
        if (search(patientId) == null) {
            return false; // nothing to delete
        }
        root = deleteRec(root, patientId);
        return true;
    }

    private PatientNode deleteRec(PatientNode node, int patientId) {
        if (node == null) {
            return null;
        }

        if (patientId < node.patient.getPatientId()) {
            node.left = deleteRec(node.left, patientId);
        } else if (patientId > node.patient.getPatientId()) {
            node.right = deleteRec(node.right, patientId);
        } else {
            // Found the node to delete

            // Case 1: no children
            if (node.left == null && node.right == null) {
                return null;
            }
            // Case 2: one child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            // Case 3: two children -> replace with the in-order successor
            // (smallest value in the right subtree)
            PatientNode successor = findMin(node.right);
            node.patient = successor.patient;
            node.right = deleteRec(node.right, successor.patient.getPatientId());
        }
        return node;
    }

    private PatientNode findMin(PatientNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // ---------- IN-ORDER TRAVERSAL ----------

    public void inOrderTraversal() {
        if (root == null) {
            System.out.println("No patient records found.");
            return;
        }
        System.out.println("---- Patients (ascending Patient ID) ----");
        inOrderRec(root);
    }

    private void inOrderRec(PatientNode node) {
        if (node == null) {
            return;
        }
        inOrderRec(node.left);
        System.out.println(node.patient);
        inOrderRec(node.right);
    }

    public boolean isEmpty() {
        return root == null;
    }
}
