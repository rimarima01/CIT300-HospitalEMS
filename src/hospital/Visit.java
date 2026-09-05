package hospital;

/**
 * Represents a single past hospital visit belonging to a patient.
 * Stored as data inside each node of that patient's VisitHistory linked list.
 */
public class Visit {
    private int visitId;
    private String visitDate;
    private String doctorName;
    private String diagnosis;
    private String treatment;

    public Visit(int visitId, String visitDate, String doctorName, String diagnosis, String treatment) {
        this.visitId = visitId;
        this.visitDate = visitDate;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public int getVisitId() {
        return visitId;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    @Override
    public String toString() {
        return String.format("VisitID: %-5d | Date: %-12s | Doctor: %-15s | Diagnosis: %-15s | Treatment: %s",
                visitId, visitDate, doctorName, diagnosis, treatment);
    }
}
