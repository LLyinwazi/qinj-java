package wiwi.qinj.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import wiwi.qinj.domain.Member;

/**
 * @author Wisya
 * @description
 * @buildTime 2018-10-14 13:29
 */
@Mapper
public interface MemberMapper {


    @Results({
            @Result(property = "memberId", column = "member_id"),
            @Result(property = "memberName", column = "member_name"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "wechatId", column = "wechat_id"),
            @Result(property = "loginPassword", column = "login_password")
    })
    @Select("SELECT * FROM member WHERE phone = #{phone}")
    Member selectByPhone(@Param("phone") String phone);

}
