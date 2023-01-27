package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;

import java.util.List;

public interface BrandService {

    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> selectAll();

    /**
     * 添加品牌
     * @param brand
     */
    void add(Brand brand);

    /**
     * 修改品牌
     * @param brand
     */
    void update(Brand brand);

    /**
     * 根据ID查询品牌
     * @return
     */
    Brand selectById(int id);

    /**
     * 根据ID删除品牌
     * @param id
     */
    void deleteById(int id);

    /**
     * 根据ID批量删除品牌
     * @param ids
     */
    void deleteByIds(int[] ids);

    /**
     * 分页查询
     * @param currentPage  当前页码
     * @param pageSize     查询条数
     * @return             PageBean
     */
    PageBean<Brand> selectByPage(int currentPage, int pageSize);

    /**
     * 根据条件分页查询
     * @param currentPage   当前页码
     * @param pageSize      查询条数
     * @param serchBrand    查询条件封装的对象
     * @return
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand serchBrand);
}
