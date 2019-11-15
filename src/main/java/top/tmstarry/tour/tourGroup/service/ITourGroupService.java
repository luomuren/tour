package top.tmstarry.tour.tourGroup.service;

import top.tmstarry.tour.base.service.IBaseService;
import top.tmstarry.tour.tourGroup.service.bean.TourGroup;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ITourGroupService
 * @Author 落幕人
 * <p>
 * 旅游团Service
 * <p>
 * @Date 2019/11/6 11:06
 * @Version 1.0
 **/
public interface ITourGroupService extends IBaseService<TourGroup> {
    public List<TourGroup> getTourByScenic(int id);

    public List<String> getScenicImgByTour(int id);

    public void upTourNum(int id);

    public void joinTour(Map<String, Object> map);

    public boolean getIsJoinTour(Map<String, Object> map);

}
