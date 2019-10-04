package com.miaosha.aop;


import com.miaosha.controller.viewobiect.UserVo;
import com.miaosha.model.UserModel;
import com.miaosha.util.IpUtils;
import com.miaosha.util.JsonUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
/**
 * @author luther
 * 切点类
 */
@Aspect
@Component
@SuppressWarnings("all")
public class SystemLogAspect {
    //注入Service用于把日志保存数据库，实际项目入库采用队列做异步
    /*@Resource
    private ActionService actionService;*/
    //本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
    //Service层切点
    @Pointcut("@annotation(com.miaosha.aop.SystemServiceLog)")
    public void serviceAspect(){
    }

    //Controller层切点
    @Pointcut("@annotation(com.miaosha.aop.SystemControllerLog)")
    public void controllerAspect(){
    }

    /**
     * @Description  前置通知  用于拦截Controller层记录用户的操作
     */

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        UserModel user = (UserModel) session.getAttribute("LOGIN_USER");
        if(user==null){
            user=new UserModel();
            user.setName("无");
        }

        String ip = IpUtils.getIpAddr(request);

        try {
            //*========控制台输出=========*//
            logger.info("==============前置通知开始==============");
            logger.info("请求方法" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
            logger.info("方法描述：" + getControllerMethodDescription(joinPoint));
            logger.info("请求人："+user.getName());
            logger.info("请求ip："+ip);

            //*========数据库日志=========*//
        }catch (Exception e){
            //记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息：{}",e.getMessage());
        }
    }
    /**
     * @Description  前置通知  用于拦截Controller层记录用户的操作
     */

    @After("serviceAspect()")
    public void doAfter(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        UserModel user = (UserModel) session.getAttribute("LOGIN_USER");
        if(user==null){
            user=new UserModel();
            user.setName("无");
        }

        String ip = IpUtils.getIpAddr(request);

        try {
            //*========控制台输出=========*//
            logger.info("==============后置通知开始==============");
            logger.info("请求方法" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
            logger.info("方法描述：" + getServiceMethodDescription(joinPoint));
            logger.info("请求人："+user.getName());
            logger.info("请求ip："+ip);

            //*========数据库日志=========*//
        }catch (Exception e){
            //记录本地异常日志
            logger.error("==后置通知Service异常==");
            logger.error("异常信息：{}",e.getMessage());
        }
    }

    /**
     * @Description  异常通知 用于拦截service层记录异常日志
     */
    @AfterThrowing(pointcut = "serviceAspect()",throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable e) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        UserVo user = (UserVo) session.getAttribute("user");
        //获取请求ip
        String ip = IpUtils.getIpAddr(request);
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs()!=null&&joinPoint.getArgs().length>0){
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params+= JsonUtils.objectToJsonStr(joinPoint.getArgs()[i])+";";
            }
        }
        try{
            /*========控制台输出=========*/
            logger.info("=====异常通知开始=====");
            logger.info("异常代码:" + e.getClass().getName());
            logger.info("异常信息:" + e.getMessage());
            logger.info("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            logger.info("方法描述:" + getServiceMethodDescription(joinPoint));
            logger.info("请求人:" + user.getName());
            logger.info("请求IP:" + ip);
            logger.info("请求参数:" + params);
            /*==========数据库日志=========*/
        }catch (Exception ex){
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
    }


    /**
     * @Description  获取注解中对方法的描述信息 用于service层注解
     */
    public static String getServiceMethodDescription(JoinPoint joinPoint)throws Exception{
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SystemServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }



    /**
     * @Description  获取注解中对方法的描述信息 用于Controller层注解
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }
}
