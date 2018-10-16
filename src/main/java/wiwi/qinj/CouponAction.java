package wiwi.qinj;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import wiwi.qinj.dao.CouponMapper;
import wiwi.qinj.domain.Coupon;

/**
 * @author Wisya
 * @description
 * @buildTime 2018-10-15 20:58
 */
@RequestMapping(value = "/", method = RequestMethod.POST)
@Controller
@Slf4j
public class CouponAction {

    @Resource
    CouponMapper couponMapper;

    @RequestMapping(value = "getAllCoupon", method = RequestMethod.GET)
    @ResponseBody
    public List<Coupon> getAllCoupon() {
        return couponMapper.getAllCoupons();
    }

}
