package ustc.sse.tims.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.component
 * @date 2019/3/13-16:36
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //通过Session获取登录用户信息
        Object user=request.getSession().getAttribute("loginUser");
        if(user == null){
            //未登录
            request.setAttribute("message","没有权限，请先登录");
            request.getRequestDispatcher("/login").forward(request,response);
            return false;

        }else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
