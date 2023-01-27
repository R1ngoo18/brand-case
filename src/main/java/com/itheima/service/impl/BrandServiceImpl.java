package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    // 获取sqlSessionFactory
    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selectAll() {
        // 1、获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2、获取BrandMapper接口代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 3、调用mapper方法返回List集合
        List<Brand> brands = mapper.selectAll();

        // 4、释放资源
        sqlSession.close();

        return brands;
    }

    @Override
    public void add(Brand brand) {
        // 1、获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2、获取BrandMapper接口代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 3、调用mapper方法进行添加操作
        mapper.add(brand);

        // 4、提交事务
        sqlSession.commit();

        // 5、释放资源
        sqlSession.close();

    }

    @Override
    public void update(Brand brand) {
        // 1、获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2、获取BrandMapper接口代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 3、调用mapper方法进行添加操作
        mapper.update(brand);

        // 4、提交事务
        sqlSession.commit();

        // 5、释放资源
        sqlSession.close();
    }

    @Override
    public Brand selectById(int id) {
        // 1、获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2、获取BrandMapper接口代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 3、调用mapper方法进行添加操作
        Brand brand = mapper.selectById(id);

        // 4、释放资源
        sqlSession.close();

        return brand;
    }

    @Override
    public void deleteById(int id) {
        // 1、获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2、获取BrandMapper接口代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 3、调用mapper方法进行删除
        mapper.deleteById(id);

        // 4、提交事务
        sqlSession.commit();

        // 5、释放资源
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        // 1、获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2、获取BrandMapper接口代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 3、调用mapper方法进行删除
        mapper.deleteByIds(ids);

        // 4、提交事务
        sqlSession.commit();

        // 5、释放资源
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        // 1、获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2、获取BrandMapper接口代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 3、通过公式计算 查询起始索引 = (当前页码 - 1) * 查询条数
        int begin = (currentPage - 1) * pageSize;

        // 4、调用Mapper方法查询当前页数据  rows
        List<Brand> rows = mapper.selectByPage(begin, pageSize);

        // 5、获取总记录数 totalCount
        int totalCount = mapper.selectTotalCount();

        // 6、封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        // 7、释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand serchBrand) {
        // 1、获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2、获取BrandMapper接口代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 3、通过公式计算 查询起始索引 = (当前页码 - 1) * 查询条数
        int begin = (currentPage - 1) * pageSize;

        // 5、处理条件对象中的brandName和companyName字符串，以对应模糊查询
        String brandName = serchBrand.getBrandName();
        if (brandName != null && brandName.length() > 0){
            serchBrand.setBrandName("%"+brandName+"%");
        }

        String companyName = serchBrand.getCompanyName();
        if (companyName != null && companyName.length() > 0){
            serchBrand.setCompanyName("%"+companyName+"%");
        }

        // 6、调用Mapper方法查询当前页数据  rows
        List<Brand> rows = mapper.selectByPageAndCondition(begin, pageSize, serchBrand);

        // 7、获取总记录数 totalCount
        int totalCount = mapper.selectTotalCountByCondition(serchBrand);

        // 8、封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        // 9、释放资源
        sqlSession.close();

        return pageBean;
    }
}
