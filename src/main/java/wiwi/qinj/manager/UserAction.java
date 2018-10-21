package wiwi.qinj.manager;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import wiwi.qinj.CommAction;
import wiwi.qinj.dao.CouponMapper;
import wiwi.qinj.domain.Coupon;
import wiwi.qinj.domain.User;

/**
 * @author Wisya
 * @description
 * @buildTime 2018-10-16 18:59
 */
@Slf4j
@Controller
@RequestMapping(value = "/", method = RequestMethod.POST)
public class UserAction extends CommAction {

    @Resource
    UserMapper userMapper;
    @Resource
    CouponMapper couponMapper;


    @RequestMapping("/createUser")
    @ResponseBody
    public String createUser(Map<String, String> params) {
        log.info("----createUser begin----" + params);
        User checkUser = userMapper.selectByPhone(params.get("phone"));
        if (checkUser != null)
            return "用户已存在";
        User user = new User();
        user.setUserId(getId());
        user.setUserName(params.get("userName"));
        user.setPhone(params.get("phone"));
        user.setPassword(params.get("password"));
        userMapper.createUser(user);
        return SUCCESS;
    }

    /**
     * 登陆
     */
    @RequestMapping("/login")
    public String doLogin(@RequestParam String phone, @RequestParam String password, Model model) {
        log.info("----------- user request login:" + phone);
        // 验证登录密码，通过后得到运营人员信息
        User user = userMapper.selectByPhone(phone);
        // 运营人员不为空 登录校验通过
        if (user != null) {
            log.info("------登陆成功------");
            // 密码置空 保存session
            user.setPassword(null);
            setSessionAttr(SESSION_USER_INFO, user);
            List<Coupon> coupons = couponMapper.getAllCoupons();
            model.addAttribute("coupons", coupons);
            Object attribute = getSessionAttribute(SESSION_USER_INFO);
            log.info("---------------get user:" + attribute);
            return "redirect:coupon/page"; //跳转到coupon页面
        } else {// 运营人员查询失败
            log.info("------登陆失败------");
            return "登陆失败";
        }
    }

}
