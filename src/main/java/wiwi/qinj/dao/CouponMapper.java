package wiwi.qinj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
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
    @ResultType(Coupon.class)
    List<Coupon> getAllCoupons();


    @Insert("insert into coupon(\n" +
            "coupon_id    ,\n" +
            "coupon_name  ,\n" +
            "pre_price    ,\n" +
            "real_price   ,\n" +
            "create_time  ,\n" +
            "total_count  ,\n" +
            "picture      ,\n" +
            "description  ,\n" +
            "status) " +
            "values(\n" +
            "#{couponId    },\n" +
            "#{couponName  },\n" +
            "#{prePrice    },\n" +
            "#{realPrice   },\n" +
            "#{createTime  },\n" +
            "#{totalCount  },\n" +
            "#{picture     },\n" +
            "#{description },\n" +
            "#{status      })")
    void createCoupon(Coupon coupon);

    @Delete("delete from coupon where coupon_id = #{couponId}")
    void delete(String couponId);
}
