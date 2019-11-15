package top.tmstarry.tour.base.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.tmstarry.tour.base.dao.IBaseDao;
import top.tmstarry.tour.base.service.IBaseService;
import top.tmstarry.tour.base.service.bean.Base;
import top.tmstarry.tour.base.service.bean.DataGridResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseService
 * @Author 落幕人
 * <p>
 * 基础Service
 * <p>
 * @Date 2019/10/26 11:17
 * @Version 1.0
 **/
public class BaseService<D extends IBaseDao<B>,B extends Base> implements IBaseService<B> {
    private static final Logger logger = LogManager.getLogger(BaseService.class);

    @Autowired
    protected D dao;

    @Override
    public DataGridResult findItemByPage(Map<String, Object> map) {
        if (map.get("page") != null) {
            int page = Integer.parseInt(map.get("page").toString());
            int rows = Integer.parseInt(map.get("rows").toString());
            map.put("startIndex", (page - 1)*rows);
            map.put("rows", rows);
        }
        DataGridResult grid = new DataGridResult();
//        PageHelper.startPage(0, 3);
        List<B> page = dao.selectList(map);
        long total = dao.listCount(map);
//        PageInfo pageInfo = new PageInfo(page);
        //获取总记录数
        grid.setRows(page);
        grid.setTotal(total);

        return grid;
    }


    /*@Override
    public List<B> list(Map<String, ?> map) {
        return dao.selectList(map);
    }*/

    @Override
    public B get(Map<String, ?> map) {
        return dao.selectOne(map);
    }

    @Override
    @Transactional
    public B add(B bean) {
        dao.insert(bean);
        return bean;
    }

    /**
     * 批量插入数据
     *
     * @param bean
     * @return
     */
    @Override
    @Transactional
    public List<B> addBatch(List<B> bean) {
        for (int i = 0; i < bean.size(); i++) {
            dao.insert(bean.get(i));
        }
        return bean ;
    }

    @Override
    @Transactional
    public B update(B bean,int id) {
        System.out.println("====================================="+bean);
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("bean", bean);
        dao.update(map);
        return bean;
    }

    @Override
    @Transactional
    public List<B> updateBatch(List<B> bean, int[] ids) {
        for (int i = 0; i < bean.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("bean", bean.get(i));
            map.put("id", ids[i]);
            dao.update(map);
        }
        return bean;
    }

    @Override
    @Transactional
    public void delete(int[] ids) {
        dao.delete(ids);
    }

    @Override
    public boolean exist(Map<String, ? extends Object> map) {
        return dao.selectOne(map)!=null;
    }
}
