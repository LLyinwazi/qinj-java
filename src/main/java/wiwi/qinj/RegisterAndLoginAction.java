package wiwi.qinj;

import java.util.ArrayList;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import wiwi.qinj.dao.MemberMapper;
import wiwi.qinj.domain.Member;

/**
 * @author Wisya
 * @description
 * @buildTime 2018-10-14 13:00
 */
@Slf4j
@Controller
@RequestMapping(value = "/", method = RequestMethod.POST)
public class RegisterAndLoginAction {


    @Resource
    private MemberMapper memberMapper;

    @RequestMapping(value = "/hello.do")
    public String hello(@RequestParam("username") String username) {
        log.info("---------hello begin----------username=" + username);
        return "successful wiwi";
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public Member getUser(@RequestParam("phone") String phone) {
        log.info("------fine by phone begin-----phone=" + phone);
        Member member = memberMapper.selectByPhone(phone);
        log.info("------get member-----member=" + member);
        return member;
    }

    @RequestMapping(value = "/member")
    public String member(Model model) {
        model.addAttribute("hello", "hello wiwi");
        return "member";
    }

    @RequestMapping(value = "/find")
    public String findByPhone(@RequestParam("phone") String phone, Model model) {
        log.info("------fine by phone begin-----phone=" + phone);
        Member member = memberMapper.selectByPhone(phone);
        log.info("------get member-----member=" + member);
        ArrayList<Member> list = new ArrayList<>();
        list.add(member);
        model.addAttribute("userList", list);
        model.addAttribute("hello", "hello wiwi");
        return "member";
    }

    @RequestMapping("/wiwi")
    public String index(ModelMap map) {
        map.addAttribute("host", "http://blog.didispace.com");
        return "index";
    }
}
