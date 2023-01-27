package com.itheima.web.old;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/addBrandServlet")
public class AddBrandServlet extends HttpServlet {
    // 实例化BrandService
    private BrandService brandService = new BrandServiceImpl();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、接收用户提交的数据JSON字符串
        BufferedReader br = request.getReader();
        String brandJSON = br.readLine();

        // 2、将JOSN字符出序列化为Brand对象
        Brand brand = JSON.parseObject(brandJSON, Brand.class);

        // 3、调用Service层方法
        brandService.add(brand);

        // 4、响应添加成功标识
        response.getWriter().write("success");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
