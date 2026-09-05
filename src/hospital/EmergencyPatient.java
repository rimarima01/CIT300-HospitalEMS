package hospital;

/**
 * Represents a patient waiting in the emergency queue.
 * Kept separate from Patient so the queue only needs the minimal
 * information required to call a patient in for treatment.
 */
public class EmergencyPatient {
    private int patientId;
    private String name;
    private String reason; // reason for emergency visit

    public EmergencyPatient(int patientId, String name, String reason) {
        this.patientId = patientId;
        this.name = name;
        this.reason = reason;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return String.format("ID: %-5d | Name: %-15s | Reason: %s", patientId, name, reason);
    }
}
