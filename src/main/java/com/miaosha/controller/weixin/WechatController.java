package com.miaosha.controller.weixin;

import com.miaosha.common.Tool;
import com.miaosha.config.StepExecutorConfig;
import com.miaosha.controller.BaseController;
import com.miaosha.service.WeixinCoreService;
import com.miaosha.util.CheckUntil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author luther
 */
@Controller("wechat")
@RequestMapping("/wechat")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class WechatController {
    private static final Logger logger = LogManager.getLogger(WechatController.class);
    @Autowired
    private WeixinCoreService weixinCoreService;


    @PostMapping("/wxToken")
    @ResponseBody
    public void wxToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        PrintWriter out=null;
        try {
            out = httpServletResponse.getWriter();
            String signature = httpServletRequest.getParameter("signature");
            String timestamp = httpServletRequest.getParameter("timestamp");
            String nonce = httpServletRequest.getParameter("nonce");
            String echostr = httpServletRequest.getParameter("echostr");
            logger.info(signature + "," + timestamp + "," + nonce + "," + echostr);
            if (CheckUntil.checkSignatures(signature, timestamp, nonce)) {
                logger.info("----------------开始处理微信发过来的消息------------------");
                // 微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
                httpServletResponse.setCharacterEncoding("UTF-8");
                // 在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
                httpServletResponse.setCharacterEncoding("UTF-8");
                String respXml = weixinCoreService.weixinMessageHandelCoreService(httpServletRequest, httpServletResponse);
                if ("".equals(Tool.toTrim(respXml))) {
                    logger.error("-------------处理微信消息失败-----------------------");
                } else {
                    logger.info("----------返回微信消息处理结果-----------------------:" + respXml);
                    out.print(respXml);
                    /*return respXml;*/
                }
            } else {
                logger.error("-------------处理微信消息失败-----------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

}
