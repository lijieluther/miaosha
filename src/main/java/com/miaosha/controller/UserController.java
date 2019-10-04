package com.miaosha.controller;

import com.alibaba.druid.util.StringUtils;
import com.miaosha.aop.SystemControllerLog;
import com.miaosha.controller.viewobiect.UserVo;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.model.UserListModel;
import com.miaosha.model.UserModel;
import com.miaosha.response.CommonReturnType;
import com.miaosha.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author luther
 */
@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 用户登陆入口
     */
    @SystemControllerLog(description = "用户登录初始化")
    @RequestMapping(value = "/loginInit", method = {RequestMethod.GET})
    public String loginInit(){
        return "/user/userLogin";
    }
    /**
     *
     * 用户注册接口
     *
     */
    @RequestMapping(value = "/register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telphone") String telphone,
                                     @RequestParam(name = "otpCode") String otpCode,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "password") String password,
                                     @RequestParam(name = "gender") Integer gender,
                                     @RequestParam(name = "age") Integer age) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //验证手机号和对应的otpCode想符合
        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telphone);
        if (!StringUtils.equals(otpCode, inSessionOtpCode)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "短信验证码不符合");
        }
        //用户的注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender(new Byte(String.valueOf(gender.intValue())));
        userModel.setAge(age);
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("byphone");
        userModel.setEncrptPassword(this.EncodeByMd5(password));

        userService.register(userModel);
        return CommonReturnType.create(null);

    }

    /**
     *
     * 用户登陆接口
     *
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telphone") String telphone,
                                  @RequestParam(name = "password") String password,HttpServletRequest request) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        //入参校验
        if (org.apache.commons.lang3.StringUtils.isEmpty(telphone) || StringUtils.isEmpty(password)) {
            throw new BusinessException((EmBusinessError.PARAMETER_VALIDATION_ERROR));
        }

        //用户登陆服务，用来校验用户登陆是否合法
        UserModel userModel=userService.validateLogin(request,telphone,this.EncodeByMd5(password));
        return CommonReturnType.create(null);
    }

    //加密密码
    public String EncodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方式
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64En = new BASE64Encoder();
        //加密字符串
        String newStr = base64En.encode(md5.digest(str.getBytes("utf-8")));
        return newStr;
    }

    /**
     *
     * 用户获取otp短信接口
     *
     */
    @RequestMapping(value = "/getotp", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telPhone") String telPhone) {

        //需要按照一定规则生成otp验证码
        Random random = new Random();
        int randomInt = random.nextInt(999999);
        randomInt += 100000;
        String otpCode = String.valueOf(randomInt);
        //将otp验证码与同用户手机号关联,使用httpsession方式绑定手机号与otpcode
        httpServletRequest.getSession().setAttribute(telPhone, otpCode);
        System.out.print("telphone=" + telPhone + ",otpCode=" + otpCode);
        //将otp短信通过短信通道发送给用户
        return CommonReturnType.create(null);
    }


    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        //调用service服务获取对应id用户对象并返回给前端
        UserModel userModel = userService.getUserById(id);

        //若获取的对应用户信息不存在
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }

        //将核心领域模型用户对象转化为可供UI使用的viewobject
        UserVo userVo = convertFromModel(userModel);

        //返回通用对象
        return CommonReturnType.create(userVo);
    }

    public UserVo convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userModel, userVo);
        return userVo;
    }

    /**
     * 使用模板引擎获取用户数据（测试）
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/userList")
    public String userList(Model model) throws Exception {
        model.addAttribute("title", "用户列表");
        model.addAttribute("hello", "Hello, Spring Boot!");
        List<UserListModel> userList = new ArrayList<>();
        userList.add(new UserListModel("小明", 25, true));
        userList.add(new UserListModel("小红", 23, false));
        model.addAttribute("userList", userList);
        return "/user/list";
    }
}
