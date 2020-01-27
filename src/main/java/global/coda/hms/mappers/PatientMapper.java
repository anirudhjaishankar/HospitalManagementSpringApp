package global.coda.hms.mappers;

import global.coda.hms.beans.PatientBean;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PatientMapper {

    @Select("SELECT phone, blood_group, name, age, gender, username, password, flatname, flatnumber, street, area, city, state, pincode, fk_role_id FROM t_patient p, t_address a, t_user u where p.fk_user_id = u.pk_user_id AND u.fk_address_id = a.pk_address_id AND p.is_deleted = false AND p.pk_patient_id = #{id}")
    @Results({@Result(property = "role", column = "fk_role_id"),
            @Result(property = "address.flatName", column = "flatname"),
            @Result(property = "address.flatNumber", column = "flatnumber"),
            @Result(property = "address.streetName", column = "street"),
            @Result(property = "address.areaName", column = "area"),
            @Result(property = "address.cityName", column = "city"),
            @Result(property = "address.stateName", column = "state"),
            @Result(property = "address.pincode", column = "pincode")})
    PatientBean getPatientById(int id);

    @Select("SELECT fk_user_id from t_patient where pk_patient_id  =#{patientId}")
    int getUserIdFromPatient(int patientId);

    @Insert("INSERT INTO t_patient(phone, blood_group, fk_user_id) VALUES(#{phone}, #{bloodGroup}, #{id})")
    @Options(useGeneratedKeys = true)
    int insertPatient(PatientBean patient);

    @Update("UPDATE t_patient SET phone = #{patient.phone}, blood_group=#{patient.bloodGroup} WHERE pk_patient_id=#{patientId}")
    int updatePatient(@Param("patient") PatientBean patient, @Param("patientId") int patientId);

    @Update("UPDATE t_patient SET is_deleted = true WHERE pk_patient_id=#{patientId}")
    int deletePatient(int patientId);

}
