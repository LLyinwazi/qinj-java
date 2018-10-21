package wiwi.qinj;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;


/**
 * 类说明：过滤器，未登录不允许访问未经授权的URL
 */
@Slf4j
@WebFilter(filterName = "testFilter", urlPatterns = "/*")
@Component
public class SecurityCheckFilter extends OncePerRequestFilter {

    @PostConstruct
    private void init() {
        log.info("---------------filter init-----------");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String errorInfo = null;       //返回的错误码信息
        // 未登录或session失效
        if (!CommAction.isLogin() && !CommAction.isAllowAnonymous()) {
            log.info("---------------not login-------------");
            errorInfo = "用户未登录:)";
            //强制设置报文头为json
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write(errorInfo);
            pw.flush();
            pw.close();
            return;
        }
        log.debug("-----have logined, do filter------------");
        // 放行
        filterChain.doFilter(request, response);
    }

}