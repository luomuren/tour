package top.tmstarry.tour.role.dao;

import org.apache.ibatis.annotations.Mapper;
import top.tmstarry.tour.base.dao.IBaseDao;
import top.tmstarry.tour.role.service.bean.Role;

import java.util.List;
import java.util.Map;

/**
 * @ClassName IRoleDao
 * @Author 落幕人
 * <p>
 * RoleDao
 * <p>
 * @Date 2019/11/4 21:38
 * @Version 1.0
 **/
@Mapper
public interface IRoleDao extends IBaseDao<Role> {
    public List<Role> getRoleByUser(int id);

    public void delRoleByUserId(int id);

    public void addUserRole(Map<String, Object> map);
}
