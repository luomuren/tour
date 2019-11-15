package top.tmstarry.tour.power.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.tmstarry.tour.base.service.bean.DataGridResult;
import top.tmstarry.tour.base.view.BaseController;
import top.tmstarry.tour.power.service.IPowerService;
import top.tmstarry.tour.power.service.bean.Power;
import top.tmstarry.tour.role.service.bean.Role;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @ClassName PowerController
 * @Author 落幕人
 * <p>
 * Power控制器
 * <p>
 * @Date 2019/11/4 22:10
 * @Version 1.0
 **/
@RestController
@RequestMapping("/power")
public class PowerController extends BaseController<IPowerService, Power> {
    private static final Logger logger = LogManager.getLogger(PowerController.class);

    @Override
    public Power add(Power bean, HttpSession session) {
        return super.add(bean,session);
    }

    /**
     * 非继承批量添加
     * @param bean
     * @return
     */
    @RequestMapping(value = "/addBatch",method = RequestMethod.POST)
    public boolean addBatch(String bean, HttpSession session) {
        if ("".equals(bean)) {
            return false;
        }
        Gson gson = new Gson();
        ArrayList<Power> beans = gson.fromJson(bean, new TypeToken<List<Power>>() {
        }.getType());
        Iterator<Power> iterator = beans.iterator();
        while (iterator.hasNext()) {
            Power b = iterator.next();
            b.setCreatTime(new Date());
            b.setUpTime(b.getCreatTime());
            b.setCreatUser((String) session.getAttribute("userName"));
            b.setUpUser(b.getCreatUser());
        }

        service.addBatch(beans);
        return true;
    }


    @Override
    public Power delete(int id) {
        return super.delete(id);
    }

    @Override
    public boolean deleteALl(String ids) {
        return super.deleteALl(ids);
    }

    @Override
    public Power update(Power bean, int id, HttpSession session) {
        return super.update(bean, id,session);
    }

    /**
     * 非继承自定义批量修改
     * @param bean
     * @param ids
     * @return
     */
    @RequestMapping(value = "/upBatch",method = RequestMethod.PUT)
    public boolean upPatch(String bean, String ids, HttpSession session) {
        if (ids.equals("")) {
            return false;
        }

        Gson gson = new Gson();
        ArrayList<Power> beans = gson.fromJson(bean, new TypeToken<List<Power>>() {
        }.getType());
        Iterator<Power> iterator = beans.iterator();
        while (iterator.hasNext()) {
            Power b = iterator.next();
            b.setUpTime(new Date());
            b.setUpUser((String) session.getAttribute("userName"));
        }

        String[] split = ids.split(",");
        int[] idss = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            idss[i] = Integer.parseInt(split[i]);
        }
        service.updateBatch(beans, idss);
        return true;
    }

    @Override
    public Power get(int id) {
        return super.get(id);
    }

    @Override
    public DataGridResult getListByPage(Map<String, Object> map) {
        return super.getListByPage(map);
    }

    @RequestMapping("/getPowerByRole/{id}")
    public List<Power> getPowerByRole(@PathVariable("id") int id) {
        List<Power> powers = service.getPowerByRole(id);
        return powers;
    }

    @RequestMapping("/upRolePower")
    public boolean upRolePower(Integer roleId, String ids) {
        String[] split = ids.split(",");
        int[] idss = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            idss[i] = Integer.parseInt(split[i]);
        }
        service.delPowerByRoleId(roleId);
        for (int i = 0; i < idss.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("roleId", roleId);
            map.put("powerId", idss[i]);
            service.addRolePower(map);
        }

        return true;
    }
}
