package top.tmstarry.tour.power.service;

import top.tmstarry.tour.base.service.IBaseService;
import top.tmstarry.tour.power.service.bean.Power;

import java.util.List;
import java.util.Map;

/**
 * @ClassName IPowerService
 * @Author 落幕人
 * <p>
 * PowerService接口
 * <p>
 * @Date 2019/11/4 22:09
 * @Version 1.0
 **/
public interface IPowerService extends IBaseService<Power> {
    public List<Power> getPowerByRole(int id);

    public void delPowerByRoleId(int id);

    public void addRolePower(Map<String, Object> map);
}
