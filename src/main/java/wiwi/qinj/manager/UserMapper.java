package wiwi.qinj.manager;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import wiwi.qinj.domain.User;

/**
 * @author Wisya
 * @description
 * @buildTime 2018-10-16 19:00
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user(user_id,user_name,phone, password) values(#{userId}, #{userName}, #{phone}, #{password})")
    void createUser(User user);


    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "password", column = "password"),
    })
    @Select("select * from user where phone = #{phone}")
    User selectByPhone(String phone);

    @Delete("delete from user where user_id=#{userId}")
    void deleteUser(String userId);

    @Update("update user set password = #{password} where user_id = #{userId}")
    void updateUserPsw(@Param("userId") String userId, @Param("password") String password);

}
