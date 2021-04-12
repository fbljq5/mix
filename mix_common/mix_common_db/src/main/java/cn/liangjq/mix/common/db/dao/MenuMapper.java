package cn.liangjq.mix.common.db.dao;


import cn.liangjq.mix.common.db.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int insert(Menu record);

    /**
     * @mbg.generated
     */
    int insertSelective(Menu record);

    /**
     * @mbg.generated
     */
    Menu selectByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Menu record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(Menu record);
}