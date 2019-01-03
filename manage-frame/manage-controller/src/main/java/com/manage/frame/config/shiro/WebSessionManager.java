package com.manage.frame.config.shiro;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/10
 * Time: 18:41
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SimpleSession;
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
import java.util.Collection;
import java.util.Date;

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
        String sid = request.getParameter("rememberId");
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
            Serializable sessionId = super.getSessionId(request, response);
            log.info("sessionId:{}",sessionId);
            return sessionId;
        }
    }

    @Override
    public void validateSessions() {
        super.validateSessions();
    }

    protected Session retrieveSession(SessionKey sessionKey) {
        try{
            return super.retrieveSession(sessionKey);
        }catch (UnknownSessionException e) {
            // 获取不到SESSION不抛出异常
            return null;
        }
    }

    public Date getStartTimestamp(SessionKey key) {
        try{
            return super.getStartTimestamp(key);
        }catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
            return null;
        }
    }

    public Date getLastAccessTime(SessionKey key) {
        try{
            return super.getLastAccessTime(key);
        }catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
            return null;
        }
    }

    public long getTimeout(SessionKey key){
        try{
            return super.getTimeout(key);
        }catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
            return 0;
        }
    }

    public void setTimeout(SessionKey key, long maxIdleTimeInMillis) {
        try{
            super.setTimeout(key, maxIdleTimeInMillis);
        }catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
        }
    }

    public void touch(SessionKey key) {
        try{
            super.touch(key);
        }catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
        }
    }

    public String getHost(SessionKey key) {
        try{
            return super.getHost(key);
        }catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
            return null;
        }
    }

    public Collection<Object> getAttributeKeys(SessionKey key) {
        try{
            return super.getAttributeKeys(key);
        }catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
            return null;
        }
    }

    public Object getAttribute(SessionKey sessionKey, Object attributeKey) {
        try{
            return super.getAttribute(sessionKey, attributeKey);
        }catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
            return null;
        }
    }

    public void setAttribute(SessionKey sessionKey, Object attributeKey, Object value) {
        try{
            super.setAttribute(sessionKey, attributeKey, value);
        }catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
        }
    }

    public Object removeAttribute(SessionKey sessionKey, Object attributeKey) {
        try{
            return super.removeAttribute(sessionKey, attributeKey);
        }catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
            return null;
        }
    }

    public void stop(SessionKey key) {
        try{
            super.stop(key);
        }catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
        }
    }

    public void checkValid(SessionKey key) {
        try{
            super.checkValid(key);
        }catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
        }
    }

    @Override
    protected Session doCreateSession(SessionContext context) {
        try{
            return super.doCreateSession(context);
        }catch (IllegalStateException e) {
            return null;
        }
    }

    @Override
    protected Session newSessionInstance(SessionContext context) {
        Session session = super.newSessionInstance(context);
        session.setTimeout(getGlobalSessionTimeout());
        return session;
    }

    @Override
    public Session start(SessionContext context) {
        try{
            return super.start(context);
        }catch (NullPointerException e) {
            SimpleSession session = new SimpleSession();
            session.setId(0);
            return session;
        }
    }
}
