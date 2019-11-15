package top.tmstarry.tour.power.service.impl;

import org.springframework.stereotype.Service;
import top.tmstarry.tour.base.service.impl.BaseService;
import top.tmstarry.tour.power.dao.IPowerDao;
import top.tmstarry.tour.power.service.IPowerService;
import top.tmstarry.tour.power.service.bean.Power;

import java.util.List;
import java.util.Map;

/**
 * @ClassName PowerService
 * @Author 落幕人
 * <p>
 * PowerServic实现类
 * <p>
 * @Date 2019/11/4 22:09
 * @Version 1.0
 **/
@Service
public class PowerService extends BaseService<IPowerDao, Power> implements IPowerService {
    @Override
    public List<Power> getPowerByRole(int id) {
        return dao.getPowerByRole(id);
    }

    @Override
    public void delPowerByRoleId(int id) {
        dao.delPowerByRoleId(id);
    }

    @Override
    public void addRolePower(Map<String, Object> map) {
        dao.addRolePower(map);
    }
}
