package com.miaosha.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author luther
 */
public interface WeixinCoreService {
   String weixinMessageHandelCoreService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
