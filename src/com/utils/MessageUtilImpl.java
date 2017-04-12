package com.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Component;

@Component("messageUtil")
public class MessageUtilImpl implements MessageUtil {

    @Override
    public void sendMessage(String phoneNum, String id) {
        //1. 打开浏览器
        HttpClient client = new HttpClient();
        //2. 创建请求的方式：get/post
        PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn/");
        //3. 设置请求的参数信息
        post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        post.setParameter("Uid", "qiyao");//参数都和上面的一样
        post.setParameter("Key", "6122d5fb2c238583d8f6");
        post.setParameter("smsMob", "18310692663");
        post.setParameter("smsText", "Hello World!");
        //4. 提交请求并返回状态码
        try {
            int code = 0;
            code = client.executeMethod(post);
            System.out.println("http返回的状态码：" + code);
            //5. 获取服务器端返回的数据信息
            String result = post.getResponseBodyAsString();
            System.out.println("短信发送结果为：" + result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            post.releaseConnection();
        }
    }
}