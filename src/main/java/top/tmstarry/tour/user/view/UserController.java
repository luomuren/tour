package top.tmstarry.tour.user.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.web.bind.annotation.*;
import top.tmstarry.tour.base.service.bean.DataGridResult;
import top.tmstarry.tour.base.view.BaseController;
import top.tmstarry.tour.role.service.bean.Role;
import top.tmstarry.tour.shiro.DatabaseRealm;
import top.tmstarry.tour.user.service.IUserService;
import top.tmstarry.tour.user.service.bean.User;
import top.tmstarry.tour.user.service.bean.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @ClassName UserController
 * @Author 落幕人
 * <p>
 * 用户控制器
 * <p>
 * @Date 2019/10/26 13:57
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<IUserService,User>{
    private static final Logger logger = LogManager.getLogger(UserController.class);

    /**
     * 单个插入数据
     * @param user
     * @return
     */
    @Override
    public User add(User user, HttpSession session) {
        return super.add(user,session);
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
        ArrayList<User> user = gson.fromJson(bean, new TypeToken<List<User>>() {
        }.getType());
        Iterator<User> iterator = user.iterator();
        while (iterator.hasNext()) {
            User b = iterator.next();
            b.setPassword("");
            b.setCreatTime(new Date());
            b.setUpTime(b.getCreatTime());
            b.setCreatUser((String) session.getAttribute("userName"));
            b.setUpUser(b.getCreatUser());
        }
        service.addBatch(user);
        Iterator<User> iterator1 = user.iterator();
        while (iterator1.hasNext()) {
            User next = iterator1.next();
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(next.getUserId());
            userInfo.setCreatTime(new Date());
            userInfo.setUpTime(next.getCreatTime());
            userInfo.setCreatUser((String) session.getAttribute("userName"));
            userInfo.setUpUser(next.getCreatUser());
            service.insertUserInfo(userInfo);
        }

        return true;
    }

    @Override
    public boolean deleteALl(String ids) {
        return super.deleteALl(ids);
    }

    @Override
    public User delete(int userId) {
        return super.delete(userId);
    }

    @Override
    public User update(User bean, int userId, HttpSession session) {
        return super.update(bean, userId,session);
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
        ArrayList<User> user = gson.fromJson(bean, new TypeToken<List<User>>() {
        }.getType());
        Iterator<User> iterator = user.iterator();
        while (iterator.hasNext()) {
            User b = iterator.next();
            b.setUpTime(new Date());
            b.setUpUser((String) session.getAttribute("userName"));
        }


        String[] split = ids.split(",");
        int[] idss = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            idss[i] = Integer.parseInt(split[i]);
        }
        service.updateBatch(user, idss);
        return true;
    }

    /**
     *通过id检索User
     * @param userId
     * @return
     */
    @Override
    public User get(int userId) {
        return super.get(userId);
    }

    /**
     * 分页检索数据
     * @param map
     * @return
     */
    @Override
    public DataGridResult getListByPage(@RequestParam Map<String, Object> map) {
        return super.getListByPage(map);
    }

    @RequestMapping("/getGuide")
    public List<User> getGuide() {
        List<User> guide = service.getGuide();
        return guide;
    }
    @RequestMapping("/getAGuide/{id}")
    public List<UserInfo> getAGuide(@PathVariable("id") int id) {
        List<UserInfo> guide = service.getAGuide(id);
        return guide;
    }
}
