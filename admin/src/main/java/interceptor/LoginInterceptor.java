package interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Carrie
 * @date 2018-8-4
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String islogin = (String) request.getSession().getAttribute("isLogin");
        if(islogin!=null && islogin.equals("yes")) {
            return true;
        }else {
            response.sendRedirect("../login.html");
            return false;
        }
    }
}
