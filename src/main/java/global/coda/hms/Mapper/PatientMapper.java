package global.coda.hms.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import global.coda.hms.beans.PatientBean;

@Mapper
public interface PatientMapper {

	@Select("SELECT phone, blood_group, name, age, gender, username, password, flatname, flatnumber, street, area, city, state, pincode FROM t_patient p, t_address a, t_user u where p.fk_user_id = u.pk_user_id AND u.fk_address_id = a.pk_address_id AND p.is_deleted = false AND p.pk_patient_id = #{id}")
	@Results({ @Result(property = "address.flatName", column = "flatname"),
			@Result(property = "address.flatNumber", column = "flatnumber"),
			@Result(property = "address.streetName", column = "street"),
			@Result(property = "address.areaName", column = "area"),
			@Result(property = "address.cityName", column = "city"),
			@Result(property = "address.stateName", column = "state"),
			@Result(property = "address.pincode", column = "pincode") })
	PatientBean getPatientById(int id);
	
	
	@Insert("INSERT INTO t_patient(phone, blood_group, fk_user_id) VALUES(#{phone}, #{bloodGroup}, #{id})")
	@Options(useGeneratedKeys = true)
	int insertPatients(PatientBean patient);

}
