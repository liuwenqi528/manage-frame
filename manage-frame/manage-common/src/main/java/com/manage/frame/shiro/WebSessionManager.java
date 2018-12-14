package com.manage.frame.shiro;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/10
 * Time: 18:41
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/11.
 * 自定义sessionId获取
 * <p>
 * 传统结构项目中，shiro从cookie中读取sessionId以此来维持会话，
 * 在前后端分离的项目中（也可在移动APP项目使用），
 * 我们选择在ajax的请求头中传递sessionId，因此需要重写shiro获取sessionId的方式。
 * 自定义MySessionManager类继承DefaultWebSessionManager类，重写getSessionId方法   
 */
@Slf4j
public class WebSessionManager extends DefaultWebSessionManager {

    private static final String AUTHORIZATION = "Authorization";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    public WebSessionManager() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
//        String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        String sid = request.getParameter("__sid");
        log.info("sid:{}", sid);
        //如果请求头中有 Authorization 则其值为sessionId
        if (!StringUtils.isEmpty(sid)) {
            if (WebUtils.isTrue(request, "__cookie")){
                HttpServletRequest rq = (HttpServletRequest)request;
                HttpServletResponse rs = (HttpServletResponse)response;
                Cookie template = getSessionIdCookie();
                Cookie cookie = new SimpleCookie(template);
                cookie.setValue(sid); cookie.saveTo(rq, rs);
            }
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sid);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sid;
        } else {
            //否则按默认规则从cookie取sessionId
            return super.getSessionId(request, response);
        }
    }
}
