package global.coda.hms.beans;

import javax.persistence.*;

@Entity
@Table(name="t_address")
public class AddressBean {

    @Id
    @Column(name="pk_address_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pkAddressId;

    @Column(name="flatnumber")
    private String flatNumber;

    @Column(name="flatname")
    private String flatName;

    @Column(name="street")
    private String streetName;

    @Column(name="area")
    private String areaName;

    @Column(name="city")
    private String cityName;

    @Column(name="state")
    private String stateName;
    private Long pincode;

    public int getPkAddressId() {
        return pkAddressId;
    }

    public void setPkAddressId(int pkAddressId) {
        this.pkAddressId = pkAddressId;
    }
    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getFlatName() {
        return flatName;
    }

    public void setFlatName(String flatName) {
        this.flatName = flatName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

}
