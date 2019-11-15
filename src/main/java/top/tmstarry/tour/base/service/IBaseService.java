package top.tmstarry.tour.base.service;

import org.springframework.transaction.annotation.Transactional;
import top.tmstarry.tour.base.service.bean.Base;
import top.tmstarry.tour.base.service.bean.DataGridResult;

import java.util.List;
import java.util.Map;

/**
 * @ClassName IBaseService
 * @Author 落幕人
 * <p>
 * 基础Service接口
 * <p>
 * @Date 2019/10/26 11:17
 * @Version 1.0
 **/
public interface IBaseService<B extends Base> {

    public DataGridResult findItemByPage(Map<String, Object> map);

    /**
     * 普通查询
     * @param map 查询条件
     * @return
     */
    /*List<B> list(Map<String, ? extends Object> map);*/

    /**
     * 查询单条数据
     * @param map 查询条件
     * @return
     */
    B get(Map<String, ? extends Object> map);

    /**
     *插入数据
     * @param bean
     * @return
     */
    @Transactional
    B add(B bean);

    /**
     * 批量插入数据
     *
     * @param bean
     * @return
     */
    @Transactional
    List<B> addBatch(List<B> bean);

    /**
     * 更新数据
     *
     * @param bean
     * @return
     */
    @Transactional
    B update(B bean, int id);

    /**
     *批量更新数据
     * @param bean
     * @return
     */
    @Transactional
    List<B> updateBatch(List<B> bean, int[] ids);

    /**
     * 批量删除数据
     * @param ids
     */
    @Transactional
    void delete(int[] ids);

    /**
     * 判断数据是否存在
     *
     * @param map
     * @return
     */
    boolean exist(Map<String, ? extends Object> map);
}
