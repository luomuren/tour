package top.tmstarry.tour.power.dao;

import org.apache.ibatis.annotations.Mapper;
import top.tmstarry.tour.base.dao.IBaseDao;
import top.tmstarry.tour.power.service.bean.Power;
import top.tmstarry.tour.role.service.bean.Role;

import java.util.List;
import java.util.Map;

/**
 * @ClassName IPowerDao
 * @Author 落幕人
 * <p>
 * PowerDao
 * <p>
 * @Date 2019/11/4 22:08
 * @Version 1.0
 **/
@Mapper
public interface IPowerDao extends IBaseDao<Power> {
    public List<Power> getPowerByRole(int id);

    public void delPowerByRoleId(int id);

    public void addRolePower(Map<String, Object> map);
}
