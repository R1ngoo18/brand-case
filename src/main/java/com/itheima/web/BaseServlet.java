package com.itheima.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 替换HttpServlet
 * 重写service()方法
 * 通过反射实现：根据访问路径分发方法
 */
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取访问路径
        String uri = req.getRequestURI();   // 访问路径为：/brand-case/brand/selectAll

        // 2、获取方法名称，截取访问路径的最后一部分
        int index = uri.lastIndexOf('/');            // lastIndexOf() 从字符串末尾开寻找该字符第一次出现的位置
        String methodName = uri.substring(index + 1);    // selectAll
        //String substring = uri.substring(index);       // 截取包前不包后 => /selectAll

        // 3、获取类对象
        // this：谁调用就代表谁
        Class<? extends BaseServlet> cls = this.getClass();


        try {
            // 4、获取方法
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 5、调用方法
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
