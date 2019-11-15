package top.tmstarry.tour.base.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.tmstarry.tour.base.service.IBaseService;
import top.tmstarry.tour.base.service.bean.Base;
import top.tmstarry.tour.base.service.bean.DataGridResult;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @ClassName BaseController
 * @Author 落幕人
 * <p>
 * Controller基类
 * <p>
 * @Date 2019/10/26 11:15
 * @Version 1.0
 **/
public class BaseController<T extends IBaseService<B>,B extends Base> {
    private static final Logger logger = LogManager.getLogger(BaseController.class);

    @Autowired
    protected T service;

    /**
     * 创建资源
     * @param bean json反序列化的实体
     * @return 返回创建的实体
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public B add(B bean, HttpSession session) {
        bean.setCreatTime(new Date());
        bean.setUpTime(bean.getCreatTime());
        bean.setCreatUser((String) session.getAttribute("userName"));
        bean.setUpUser(bean.getCreatUser());
        this.service.add(bean);
        return bean;
    }


    /**
     * 删除资源
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public B delete(@PathVariable("id") int id) {
        this.service.delete(new int[]{id});
        return null;
    }

    /**
     * 批量删除资源
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE)
    public boolean deleteALl(String ids) {
        if (ids.equals("")) {
            return false;
        }
        String[] split = ids.split(",");
        int[] idss = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            idss[i] = Integer.parseInt(split[i]);
        }

        this.service.delete(idss);
        return true;
    }


    /**
     *更新资源
     * @param bean
     * @param id
     * @return 更新后的完整数据
     */
    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public B update(@RequestBody B bean, @PathVariable("id") int id,HttpSession session) {
        bean.setUpTime(new Date());
        bean.setUpUser((String) session.getAttribute("userName"));
        return this.service.update(bean,id);
    }

    /**
     * 查询单个：
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public B get(@PathVariable("id") int id) {
        Map<String, Integer> map = new HashMap() {{
            put("id", id);
        }};

        return service.get(map);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public DataGridResult getListByPage(@RequestParam Map<String, Object> map) {
        return service.findItemByPage(map);
    }
}
