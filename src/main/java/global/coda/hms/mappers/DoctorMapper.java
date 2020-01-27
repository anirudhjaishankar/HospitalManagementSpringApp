package global.coda.hms.mappers;

import global.coda.hms.beans.DoctorBean;
import org.apache.ibatis.annotations.*;

public interface DoctorMapper {
    @Select("SELECT phone, qualification, name, age, gender, username, password, flatname, flatnumber, street, area, city, state, pincode, fk_role_id FROM t_doctor d, t_address a, t_user u where d.fk_user_id = u.pk_user_id AND u.fk_address_id = a.pk_address_id AND d.is_deleted = false AND d.pk_doctor_id = #{id}")
    @Results({@Result(property = "role", column = "fk_role_id"),
            @Result(property = "address.flatName", column = "flatname"),
            @Result(property = "address.flatNumber", column = "flatnumber"),
            @Result(property = "address.streetName", column = "street"),
            @Result(property = "address.areaName", column = "area"),
            @Result(property = "address.cityName", column = "city"),
            @Result(property = "address.stateName", column = "state"),
            @Result(property = "address.pincode", column = "pincode")})
    DoctorBean getDoctorById(int id);

    @Select("SELECT fk_user_id from t_doctor where pk_Doctor_id  =#{DoctorId}")
    int getUserIdFromDoctor(int doctorId);

    @Insert("INSERT INTO t_doctor(phone, qualification, fk_user_id) VALUES(#{phone}, #{speciality}, #{id})")
    @Options(useGeneratedKeys = true)
    int insertDoctor(DoctorBean doctor);

    @Update("UPDATE t_doctor SET phone = #{Doctor.phone}, qualification=#{doctor.speciality} WHERE pk_doctor_id=#{doctorId}")
    int updateDoctor(@Param("Doctor") DoctorBean Doctor, @Param("DoctorId") int DoctorId);

    @Update("UPDATE t_doctor SET is_deleted = true WHERE pk_doctor_id=#{DoctorId}")
    int deleteDoctor(int DoctorId);

}
