package wiwi.qinj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import wiwi.qinj.domain.Coupon;

/**
 * @author Wisya
 * @description
 * @buildTime 2018-10-15 21:09
 */
@Mapper
public interface CouponMapper {


    @Select("select * from coupon")
    List<Coupon> getAllCoupons();

}
