package global.coda.hms.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import global.coda.hms.beans.AddressBean;

@Mapper
public interface AddressMapper {

	@Select("SELECT flatname, flatnumber, street, area, city, state, pincode FROM t_address WHERE pk_address_id = #{id}")
	AddressBean getAddressById(int addressId);
	
	@Insert("INSERT INTO t_address(flatname, flatnumber, street, area, city, state, pincode) VALUES(#{flatName}, #{flatNumber}, #{streetName}, #{areaName}, #{cityName}, #{stateName}, #{pincode})")
	@Options(useGeneratedKeys = true, keyProperty="id", keyColumn="pk_address_id")
	int insertAddress(AddressBean address);
}
