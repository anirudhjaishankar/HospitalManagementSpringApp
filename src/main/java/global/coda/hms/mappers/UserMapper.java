package global.coda.hms.mappers;

import global.coda.hms.beans.UserBean;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT fk_address_id from t_user WHERE pk_user_id=#{userId}")
    int getAddressIdFromUser(int userId);

    @Insert("INSERT INTO t_user(name, age, gender, fk_role_id, username, password, fk_address_id) VALUES(#{name}, #{age}, #{gender}, #{role}, #{username}, #{password}, #{address.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "pk_user_id")
    int insertUser(UserBean user);

    @Update("UPDATE t_user SET name=#{newUser.name}, age=#{newUser.age}, gender=#{newUser.gender}, username=#{newUser.username}, password=#{newUser.password} WHERE pk_user_id = #{userId}")
    int updateUser(@Param("newUser") UserBean newUser, @Param("userId") int userId);


}
