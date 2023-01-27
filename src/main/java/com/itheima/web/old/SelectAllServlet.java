package com.itheima.web.old;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {
    // 1、实例化BrandService
    private BrandService brandService = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // 2、调用Service层方法返回List集合
        List<Brand> brands = brandService.selectAll();

        // 3、将对象集合转换为JSON字符串
        String brandsJSON = JSON.toJSONString(brands);

        //System.out.println(brandsJSON);

        // 4、将JSON字符串响应到前台
        response.setContentType("html/json;charset=utf-8");
        response.getWriter().write(brandsJSON);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
