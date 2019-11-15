package top.tmstarry.tour.role.view;

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
import top.tmstarry.tour.role.service.IRoleService;
import top.tmstarry.tour.role.service.bean.Role;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @ClassName RoleController
 * @Author 落幕人
 * <p>
 * Role控制器
 * <p>
 * @Date 2019/11/4 21:40
 * @Version 1.0
 **/
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<IRoleService, Role> {
    private static final Logger logger = LogManager.getLogger(RoleController.class);

    @Override
    public Role add(Role bean, HttpSession session) {
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
        logger.info(bean);
        Gson gson = new Gson();
        ArrayList<Role> role = gson.fromJson(bean, new TypeToken<List<Role>>() {
        }.getType());
        Iterator<Role> iterator = role.iterator();
        while (iterator.hasNext()) {
            Role b = iterator.next();
            b.setCreatTime(new Date());
            b.setUpTime(b.getCreatTime());
            b.setCreatUser((String) session.getAttribute("userName"));
            b.setUpUser(b.getCreatUser());
        }
        logger.info(role);

        service.addBatch(role);
        return true;
    }

    @Override
    public Role delete(int id) {
        return super.delete(id);
    }

    @Override
    public boolean deleteALl(String ids) {
        return super.deleteALl(ids);
    }

    @Override
    public Role update(Role bean, int id, HttpSession session) {
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
        ArrayList<Role> role = gson.fromJson(bean, new TypeToken<List<Role>>() {
        }.getType());
        Iterator<Role> iterator = role.iterator();
        while (iterator.hasNext()) {
            Role b = iterator.next();
            b.setUpTime(new Date());
            b.setUpUser((String) session.getAttribute("userName"));
        }


        String[] split = ids.split(",");
        int[] idss = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            idss[i] = Integer.parseInt(split[i]);
        }
        service.updateBatch(role, idss);
        return true;
    }

    @Override
    public Role get(int id) {
        return super.get(id);
    }

    @Override
    public DataGridResult getListByPage(Map<String, Object> map) {
        return super.getListByPage(map);
    }

    @RequestMapping("/getRoleByUser/{id}")
    public List<Role> getRoleByUser(@PathVariable("id") int id) {
        List<Role> roleByUser = service.getRoleByUser(id);
        return roleByUser;
    }

    @RequestMapping("/upUserRole")
    public boolean upUserRole(Integer userId, String ids) {
        String[] split = ids.split(",");
        int[] idss = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            idss[i] = Integer.parseInt(split[i]);
        }
        service.delRoleByUserId(userId);
        for (int i = 0; i < idss.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", userId);
            map.put("roleId", idss[i]);
            service.addUserRole(map);
        }

        return true;
    }


}
