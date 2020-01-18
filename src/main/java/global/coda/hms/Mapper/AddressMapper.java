package global.coda.hms.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import global.coda.hms.beans.AddressBean;

@Mapper
public interface AddressMapper {

    @Select("SELECT flatname, flatnumber, street, area, city, state, pincode FROM t_address WHERE pk_address_id = #{id}")
    AddressBean getAddressById(int addressId);

    @Insert("INSERT INTO t_address(flatname, flatnumber, street, area, city, state, pincode) VALUES(#{flatName}, #{flatNumber}, #{streetName}, #{areaName}, #{cityName}, #{stateName}, #{pincode})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "pk_address_id")
    int insertAddress(AddressBean address);

    @Update("UPDATE t_address SET flatname=#{address.flatName} , flatnumber=#{address.flatNumber}, street=#{address.streetName}, area=#{address.areaName}, city=#{address.cityName}, state=#{address.stateName}, pincode=#{address.pincode} WHERE pk_address_id = #{addressId}")
    int updateAddress(@Param("address") AddressBean address,@Param("addressId") int addressId);
}
