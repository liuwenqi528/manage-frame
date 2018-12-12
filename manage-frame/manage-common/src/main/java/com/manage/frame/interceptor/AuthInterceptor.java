package com.manage.frame.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户权限拦截器，验证当前用户是否有当前接口的请求权限，如果有，则进行下一步，如果没有，则返回用户权限不足
 *
 * @author
 */
public class AuthInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    /**
     * 验证当前请求是否拥有权限
     *
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @param o        方法信息(MethodInfo)
     * @return 是否继续往下执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //        //校验session中的用户信息
//        Object obj = request.getSession().getAttribute(Consts.SESSION_USER);
//        SsoUserInfo userInfo = ( SsoUserInfo )obj;
        String url = request.getServletPath();
        logger.debug("请求地址：{}",url);
//        boolean auth = authService.authenticationByUserAndMenu(userInfo.getUserId(),url);
//        //如果当前不具有权限
//        if(!auth){
//            try {
//                response.setContentType("text/html;charset=utf-8");
//                response.getWriter().write(Jsons.toJson(JsonResult.fail(HttpStatus.UNAUTHORIZED.value(), "当前用户权限不足")));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return false;
//        }
        return true;
    }

}
