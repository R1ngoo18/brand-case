package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {

    /**
     * 查询所有品牌
     * @return 所有品牌对象的集合
     */
    @ResultMap("brandResultMap")
    @Select("select * from tb_brand")
    List<Brand> selectAll();

    /**
     * 添加品牌
     * @param brand
     */
    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    /**
     * 修改品牌
     * @param brand
     */
    void update(Brand brand);

    /**
     * 根据ID查询品牌
     * @param id
     * @return
     */
    @ResultMap("brandResultMap")
    @Select("select * from tb_brand where id = #{id}")
    Brand selectById(int id);

    /**
     * 删除品牌
     * @param id
     */
    @Delete("delete from tb_brand where id = #{id}")
    void deleteById(int id);

    /**
     * 批量删除
     * @param ids 多个id
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 分页查询
     * @param begin 起始索引
     * @param size  查询记录数
     * @return  当前页记录
     */
    @ResultMap("brandResultMap")
    @Select("select * from tb_brand limit #{begin} , #{size}")
    List<Brand> selectByPage(@Param("begin") int begin,@Param("size") int size);

    /**
     * 查询总记录数
     * @return 总记录数
     */
    @Select("select count(*) from tb_brand")
    int selectTotalCount();


    /**
     * 根据条件分页查询
     * @param begin   查询起始索引
     * @param size    查询记录条数
     * @param brand   查询条件对象
     * @return
     */
    List<Brand> selectByPageAndCondition(@Param("begin") int begin,
                                         @Param("size") int size,
                                         @Param("brand") Brand brand);

    /**
     * 条件查询总记录数
     * @return 总记录数
     */
    int selectTotalCountByCondition(Brand brand);
}
