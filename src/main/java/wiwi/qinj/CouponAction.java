package wiwi.qinj;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import wiwi.qinj.dao.CouponMapper;
import wiwi.qinj.domain.Coupon;
import wiwi.qinj.utils.IDGenerator;
import wiwi.qinj.utils.IOUtils;

/**
 * @author Wisya
 * @description
 * @buildTime 2018-10-15 20:58
 */
@RequestMapping(value = "/coupon")
@Controller
@Slf4j
public class CouponAction extends CommAction {

    @Value("${web.upload_path}")
    private String coupon_folder;

    @Resource
    CouponMapper couponMapper;

    /**
     * 获取所有优惠券
     * @param model
     * @return
     */
    @RequestMapping(value = "/getAllCoupon", method = RequestMethod.POST)
    @ResponseBody
    public Object getAllCoupon(Model model) {
        List<Coupon> couponList = couponMapper.getAllCoupons();
        boolean b = model.containsAttribute(SESSION_USER_INFO);
        log.info("---------------attr:" + b);
        Object attribute = getSessionAttribute(SESSION_USER_INFO);
        log.info("---------------get user:" + attribute);
        model.addAttribute("coupons", couponList);
        return couponList;
    }

    @RequestMapping(value = "toAdd")
    public String addPage(Coupon coupon) {
        return "addCouponPage";
    }

    /**
     * 添加优惠券
     * @param coupon
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Coupon coupon, @RequestParam("picture_file") MultipartFile picture) {
        log.info("--------------add coupon begin:" + coupon);
        coupon.setCouponId(IDGenerator.nextId());
        coupon.setCreateTime(new Date());
        coupon.setStatus("1");
        Object curr_user = getSessionAttribute(SESSION_USER_INFO);
        log.debug("---------------user info:" + curr_user);
        try {
            byte[] bytes = picture.getBytes();
            String file_name = IDGenerator.nextId() + ".png";
            log.debug("----------file_name=" + file_name);
            log.debug("----------pic_base_path=" + coupon_folder);
            // 保存文件
            String path = IOUtils.write2file(bytes, coupon_folder + file_name);
            log.debug("-------write2file end------path=" + path);
            coupon.setPicture("/" + file_name);
        } catch (IOException e) {
        }
        couponMapper.createCoupon(coupon);
        return "redirect:page";
    }

    @RequestMapping("delete")
    public String delete(String couponId, Model model) {
        log.info("-------------delete coupon begin:" + couponId);
        couponMapper.delete(couponId);
        Map<String, Object> map = model.asMap();
        Object coupons = map.get("coupons");
        log.info("-----------view:" + coupons);
        model.addAttribute("coupons", couponMapper.getAllCoupons());
        return "coupon";
    }

    @RequestMapping(value = "page")
    public String page(Model model) {
        log.debug("-------------page begin:--------------");
        model.addAttribute("coupons", couponMapper.getAllCoupons());
        return "coupon";
    }

}
