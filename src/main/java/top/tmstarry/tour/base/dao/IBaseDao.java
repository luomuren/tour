package top.tmstarry.tour.base.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.tmstarry.tour.base.service.bean.Base;

import java.util.List;
import java.util.Map;

/**
 * @ClassName IBaseDao
 * @Author 落幕人
 * <p>
 * 基础Dao接口
 * <p>
 * @Date 2019/10/26 11:18
 * @Version 1.0
 **/
@Mapper
@Repository
public interface IBaseDao<B extends Base> {
    /**
     *分页查询，返回数据
     * @param map
     * @return
     */
    List<B> selectList(Map<String, ? extends Object> map);

    /**
     * 分页查询，返回总记录条数
     * @param map
     * @return
     */
    int listCount(Map<String, ? extends Object> map);

    /**
     *查询多行数据
     * @param map
     * @return
     */
    /*List<B> selectList(Map<String, ? extends Object> map);*/

    /**
     * 查询单行数据
     * @param map
     * @return
     */
    B selectOne(Map<String, ? extends Object> map);

    /**
     * 插入数据
     * @param bean
     * @return
     */
    int insert(B bean);

    /**
     * 更新数据
     * @param map
     * @return
     */
    int update(Map<String, ? extends Object> map);



    /**
     * 删除数据
     * @param ids
     * @return
     */
    int delete(int[] ids);
}
