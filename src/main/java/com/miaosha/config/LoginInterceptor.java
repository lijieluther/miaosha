package com.miaosha.config;

import com.miaosha.controller.viewobiect.UserVo;
import com.miaosha.model.UserModel;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author luther
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**
     *
     * 这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆。
        HttpSession session = request.getSession();
        Boolean IS_LOGIN= (Boolean) session.getAttribute("IS_LOGIN");
        //这里的User是登陆时放入session的
        UserModel user = (UserModel) session.getAttribute("LOGIN_USER");
       /* String methods=((HandlerMethod) handler).getMethod().getName();
        if(methods.contains("loginInit")||methods.contains("login")||methods.contains("userList")||methods.contains("wxToken")){
            return true;
        }*/
        //如果session中没有user，表示没登陆
        if (IS_LOGIN==null||user == null){
            //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
            //当然你可以利用response给用户返回一些提示信息，告诉他没登陆
           /* response.sendRedirect("/user/loginInit");*/
            return true;
        }else {
            /**
             *
             * 如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
             *
             */
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}