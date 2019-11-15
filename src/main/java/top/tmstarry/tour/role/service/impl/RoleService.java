package top.tmstarry.tour.role.service.impl;

import org.springframework.stereotype.Service;
import top.tmstarry.tour.base.service.impl.BaseService;
import top.tmstarry.tour.role.dao.IRoleDao;
import top.tmstarry.tour.role.service.IRoleService;
import top.tmstarry.tour.role.service.bean.Role;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RoleService
 * @Author 落幕人
 * <p>
 * Roleservice实现类
 * <p>
 * @Date 2019/11/4 21:39
 * @Version 1.0
 **/
@Service
public class RoleService extends BaseService<IRoleDao, Role> implements IRoleService {
    public List<Role> getRoleByUser(int id) {
        return dao.getRoleByUser(id);
    }

    @Override
    public void delRoleByUserId(int id) {
        dao.delRoleByUserId(id);
    }

    @Override
    public void addUserRole(Map<String, Object> map) {
        dao.addUserRole(map);
    }
}
