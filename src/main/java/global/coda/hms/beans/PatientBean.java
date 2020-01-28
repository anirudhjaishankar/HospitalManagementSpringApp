package global.coda.hms.beans;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name="t_patient")
@SQLDelete(sql="UPDATE t_patient SET is_deleted=true WHERE pk_patient_id = ?")
@Where(clause = "is_deleted=0")
public class PatientBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pkPatientId;

    private String phone;
    private String bloodGroup;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_user_id")
    private UserBean userDetails;

    @Column(name="is_deleted")
    private boolean isDeleted;



    public int getPkPatientId() {
        return pkPatientId;
    }

    public void setPkPatientId(int pkPatientId) {
        this.pkPatientId = pkPatientId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public UserBean getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserBean userDetails) {
        this.userDetails = userDetails;
    }

}
