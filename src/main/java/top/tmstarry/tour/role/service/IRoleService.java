package top.tmstarry.tour.role.service;

import top.tmstarry.tour.base.service.IBaseService;
import top.tmstarry.tour.role.service.bean.Role;

import java.util.List;
import java.util.Map;

/**
 * @ClassName IRoleService
 * @Author 落幕人
 * <p>
 * Roleservice接口
 * <p>
 * @Date 2019/11/4 21:38
 * @Version 1.0
 **/
public interface IRoleService extends IBaseService<Role> {
    public List<Role> getRoleByUser(int id);

    public void delRoleByUserId(int id);

    public void addUserRole(Map<String, Object> map);
}
