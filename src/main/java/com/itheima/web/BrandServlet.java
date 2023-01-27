package com.itheima.web;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    // 0、实例化BrandService
    private BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、调用Service层方法返回List集合
        List<Brand> brands = brandService.selectAll();

        // 2、将对象集合转换为JSON字符串
        String brandsJSON = JSON.toJSONString(brands);

        //System.out.println(brandsJSON);

        // 3、将JSON字符串响应到前台
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(brandsJSON);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、接收用户提交的数据JSON字符串
        BufferedReader br = request.getReader();
        String brandJSON = br.readLine();

        // 2、将JOSN字符出序列化为Brand对象
        Brand brand = JSON.parseObject(brandJSON, Brand.class);

        // 3、调用Service层方法进行修改
        brandService.update(brand);

        // 4、响应修改成功标识
        response.getWriter().write("success");
    }

    public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、接收用户提交的ID数据
        String id = request.getParameter("id");

        // 2、调用Service层方法
        Brand brand = brandService.selectById(Integer.parseInt(id));

        // 3、将brand对象转换成JSON字符串返回
        String brandJSON = JSON.toJSONString(brand);

        // System.out.println(brandJSON);

        // 4、响应回前台页面
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(brandJSON);

    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、接收从页面获取的ID数据
        String id = request.getParameter("id");

        // 2、调用Service层方法
        brandService.deleteById(Integer.parseInt(id));

        // 3、响应删除成功标识
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、接收从页面获取的ID数据 [1,2,3]
        BufferedReader br = request.getReader();
        String idsJSON = br.readLine();

        // 2、将JSON字符串转换为int数组
        int[] ids = JSON.parseObject(idsJSON, int[].class);

        // 2、调用Service层方法
        brandService.deleteByIds(ids);

        // 3、响应删除成功标识
        response.getWriter().write("success");
    }

    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、接收前台提交的 当前页码 和 每页查询条数 ?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 2、调用Service层方法传入当前页码和每页查询条数
        PageBean<Brand> PageBean = brandService.selectByPage(currentPage,pageSize);

        // 3、将PageBean对象转换成JSON字符串返回
        String jsonString = JSON.toJSONString(PageBean);

        // 4、响应回前台页面
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、接收前台提交的 当前页码 和 每页查询条数 ?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 2、获取条件对象的JSON字符串
        BufferedReader br = request.getReader();
        String serchBrandJson = br.readLine();

        // 3、将条件对象JSON转换成真正的Brand对象
        Brand serchBrand = JSON.parseObject(serchBrandJson, Brand.class);

        // 2、调用Service层方法传入当前页码和每页查询条数以及条件对象
        PageBean<Brand> PageBean = brandService.selectByPageAndCondition(currentPage,pageSize,serchBrand);

        // 3、将PageBean对象转换成JSON字符串返回
        String jsonString = JSON.toJSONString(PageBean);

        // 4、响应回前台页面
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }
}
