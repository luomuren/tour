package top.tmstarry.tour.tourGroup.service.impl;

import org.springframework.stereotype.Service;
import top.tmstarry.tour.base.service.impl.BaseService;
import top.tmstarry.tour.tourGroup.dao.ITourGroupDao;
import top.tmstarry.tour.tourGroup.service.ITourGroupService;
import top.tmstarry.tour.tourGroup.service.bean.TourGroup;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TourGroupService
 * @Author 落幕人
 * <p>
 * 旅游团Service实现类
 * <p>
 * @Date 2019/11/6 11:06
 * @Version 1.0
 **/
@Service
public class TourGroupService extends BaseService<ITourGroupDao, TourGroup> implements ITourGroupService {

    @Override
    public List<TourGroup> getTourByScenic(int id) {
        return dao.getTourByScenic(id);
    }

    @Override
    public List<String> getScenicImgByTour(int id) {
        return dao.getScenicImgByTour(id);
    }

    @Override
    public void upTourNum(int id) {
        dao.upTourNum(id);
    }

    @Override
    public void joinTour(Map<String, Object> map) {
        dao.joinTour(map);
    }

    @Override
    public boolean getIsJoinTour(Map<String, Object> map) {
        TourGroup isJoinTour = dao.getIsJoinTour(map);
        if (isJoinTour != null) {
            return true;
        } else {
            return false;
        }
    }


}
