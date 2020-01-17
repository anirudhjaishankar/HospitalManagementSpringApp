package global.coda.hms.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import global.coda.hms.beans.UserBean;

@Mapper
public interface UserMapper {
	
	@Insert("INSERT INTO t_user(name, age, gender, fk_role_id, username, password, fk_address_id) VALUES(#{name}, #{age}, #{gender}, 3, #{username}, #{password}, #{address.id})")
	@Options(useGeneratedKeys = true, keyProperty="id", keyColumn="pk_user_id")
	int insertUser(UserBean user);
}
