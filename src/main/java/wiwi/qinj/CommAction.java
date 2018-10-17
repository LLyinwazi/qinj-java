package wiwi.qinj;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Wisya
 * @description
 * @buildTime 2018-10-16 19:27
 */
public class CommAction {

    /**
     * 项目前缀
     */
    public static final String PREFIX = "qinj:";

    /**
     * 响应成功
     */
    public static final String SUCCESS = "0000";

    public static final String SESSION_USER_INFO = "currentUser";

    int count = 100;

    /***
     * 允许匿名访问的页面
     */
    public static final String[] ALLOW_ANONYMOUS_LIST = {"/login", "/logout"};


    public static boolean isLogin() {
        return getSessionAttribute(SESSION_USER_INFO) != null;
    }

    /**
     * 判断当前请求是否为允许匿名访问，不需要授权也可访问
     * @return
     */
    public static boolean isAllowAnonymous() {
        String resource = currentRequest().getRequestURI();
        //不需要权限认证的路径
        for (String allowedUrl : ALLOW_ANONYMOUS_LIST) {
            if (resource.contains(allowedUrl)) //访问路径在范围内
                return true;
        }
        return false;
    }

    /***
     *  获取当前页面的Session对象
     */
    public static Object getSessionAttribute(String attriName) {
        HttpServletRequest hsr = currentRequest();
        return hsr.getSession().getAttribute(attriName);
    }

    /***
     *  获取当前页面的Request对象
     */
    public static HttpServletRequest currentRequest() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest hsr = sra.getRequest();
        return hsr;
    }

    /**
     * 获取自增id
     */
    public synchronized String getId() {
        return String.valueOf(System.currentTimeMillis()) + getIndex();
    }

    private int getIndex() {
        if (count > 990)
            count = 100;
        return count++;
    }

}
