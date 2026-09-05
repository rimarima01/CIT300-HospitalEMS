package hospital;

/**
 * Represents a completed treatment, pushed onto the TreatmentStack
 * once a patient's emergency treatment is finished.
 */
public class TreatmentRecord {
    private int patientId;
    private String patientName;
    private String treatmentGiven;
    private String dateTime;

    public TreatmentRecord(int patientId, String patientName, String treatmentGiven, String dateTime) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.treatmentGiven = treatmentGiven;
        this.dateTime = dateTime;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getTreatmentGiven() {
        return treatmentGiven;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return String.format("PatientID: %-5d | Name: %-15s | Treatment: %-20s | Completed: %s",
                patientId, patientName, treatmentGiven, dateTime);
    }
}
