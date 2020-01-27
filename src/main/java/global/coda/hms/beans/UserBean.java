package global.coda.hms.beans;

import javax.persistence.*;

@Entity
@Table(name="t_user")
public class UserBean {
    @Id
    @Column(name="pk_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pkUserId;

    @Column(name="fk_role_id")
    private int role;

    private String name;

    private int age;
    private String gender;
    private String username;
    private String password;

    @OneToOne
    @JoinColumn(name="fk_address_id")
    private AddressBean address;

    public int getPkUserId() {
        return pkUserId;
    }

    public void setPkUserId(int pkUserId) {
        this.pkUserId = pkUserId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

}
