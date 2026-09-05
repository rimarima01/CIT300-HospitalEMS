package hospital;

/**
 * A single node in the Patient Binary Search Tree.
 * Keyed on Patient ID: left subtree holds smaller IDs, right subtree holds larger IDs.
 */
public class PatientNode {
    Patient patient;
    PatientNode left;
    PatientNode right;

    public PatientNode(Patient patient) {
        this.patient = patient;
        this.left = null;
        this.right = null;
    }
}
