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

    public static boolean isLogin() {
        return getSessionAttribute(SESSION_USER_INFO) != null;
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