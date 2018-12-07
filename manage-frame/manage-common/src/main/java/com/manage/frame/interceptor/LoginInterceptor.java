package com.manage.frame.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息拦截器，用于验证用户是否登录，如果未登录，则返回用户未登录信息，如果已登录，则跳转到下级页面
 *
 * @author Wangyihua
 */
public class LoginInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * 请求前验证用户是否已经登录
     *
     * @param httpServletRequest  {@link HttpServletRequest}
     * @param httpServletResponse {@link HttpServletResponse}
     * @param o                   方法信息(MethodInfo)
     * @return 是否继续往下执行
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        //校验session中的用户信息
        logger.info("当前请求地址：{}", httpServletRequest.getServletPath());
        logger.info("当前请求方式：{}", httpServletRequest.getMethod());

        String method = httpServletRequest.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }

        String userId = httpServletRequest.getParameter("userId");
        if (StringUtils.isEmpty(userId)) {
//            Object obj = httpServletRequest.getSession().getAttribute(Consts.SESSION_USER);

//            logger.info("当前登录用户：{}", userId);
            //如果用户未登录，则跳转到SSO获取用户登录地址

//            if (!(obj instanceof SsoUserInfo)) {

//            SsoUserInfo userInfo = new SsoUserInfo();
//            userInfo.setUserId("1");
//            userInfo.setManageOrgName("测试支部");
//            userInfo.setManageOrgCode("1");
//            userInfo.setSuperior(false);
//            userInfo.setHash("1");
//            userInfo.setSex("1");
//            userInfo.setName("张三");
//            userInfo.setOrgId("1001001005");
//            userInfo.setUsername("zhangsan");
//            obj = userInfo;
//            httpServletRequest.getSession().setAttribute(Consts.SESSION_USER, obj);
//                try {
            //未登录, 返回登录地址
//                    String responseOrigin = SpringContextHolder.getApplicationContext().getEnvironment().getProperty("app.sso.url.response-origin");

//                    httpServletResponse.setHeader("Access-Control-Allow-Origin", responseOrigin);
//                    httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT,HEAD,TRACE,PATCH");
//                    httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
//                    httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
//                    httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
//                    httpServletResponse.setContentType("text/html;charset=utf-8");

//                String authUrl = SpringContextHolder.getApplicationContext().getEnvironment().getProperty("app.sso.url.authorize-code");
//                    String logoutSuccess = SpringContextHolder.getApplicationContext().getEnvironment().getProperty("app.sso.url.logout-success");
//                    String logout = SpringContextHolder.getApplicationContext().getEnvironment().getProperty("app.sso.url.logout");
//                String clientId = SpringContextHolder.getApplicationContext().getEnvironment().getProperty("app.sso.client-id");

//                callback = URLEncoder.encode(callback,"UTF-8");
//                String loginUrl = authUrl + "?response_type=code&redirect_uri=" + callback + "&client_id=" + clientId;
//                    logout = logout + "?Referer=" + logoutSuccess;
//                    httpServletResponse.getWriter().write(Jsons.toJson(JsonResult.fail(HttpStatus.UNAUTHORIZED.value(), "用户未登录").data(logout)));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

//                return false;
//            }
        }
        return true;
    }
}
