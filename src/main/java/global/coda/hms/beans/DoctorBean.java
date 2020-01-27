package global.coda.hms.beans;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_doctor")
@Where(clause = "is_deleted=0")
public class DoctorBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pkDoctorId;
    private String phone;

    @Column(name = "qualification")
    private String speciality;

    @OneToOne
    @JoinColumn(name = "fk_user_id")
    private UserBean userDetails;

    @OneToMany
    @JoinTable(name = "t_patient_doctor_mapping",
            joinColumns = @JoinColumn(name = "fk_doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_patient_id")
    )
    private List<PatientBean> patientList;

    @Column(name="is_deleted")
    private boolean isDeleted;

    public int getPkDoctorId() {
        return pkDoctorId;
    }

    public void setPkDoctorId(int pkDoctorId) {
        this.pkDoctorId = pkDoctorId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public UserBean getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserBean userDetails) {
        this.userDetails = userDetails;
    }

    public List<PatientBean> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<PatientBean> patientList) {
        this.patientList = patientList;
    }


}
