package com.miaosha.service;

import com.miaosha.aop.SystemServiceLog;
import com.miaosha.error.BusinessException;
import com.miaosha.model.UserModel;

import javax.servlet.http.HttpServletRequest;

/**
 * @author luther
 */
public interface UserService {
    /**
     * 通过用户ID获取用户对象的方法
     */
    UserModel getUserById(Integer id);
    void register(UserModel userModel) throws BusinessException;

    /**
     *
     * @param telphone   用户注册手机
     * @param encrptPassword    用户加密后的密码
     * @throws BusinessException
     */
    UserModel validateLogin(HttpServletRequest request,String telphone, String encrptPassword) throws BusinessException;
}
