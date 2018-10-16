package wiwi.qinj.manager;

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
import wiwi.qinj.domain.User;
import wiwi.qinj.utils.RedisService;

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
    RedisService redisService;


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
    @ResponseBody
    public String doLogin(@RequestParam String phone, @RequestParam String password, Model model) {
        log.info("----------- user request login:" + phone);
        // 验证登录密码，通过后得到运营人员信息
        User user = userMapper.selectByPhone(phone);
        // 运营人员不为空 登录校验通过
        if (user != null) {
            // 后台保存session
            model.addAttribute(CommAction.SESSION_USER_INFO, user);
            return SUCCESS;//返回所有有权限的菜单请求
        } else {// 运营人员查询失败
            //
            return "登陆失败";
        }
    }

}
