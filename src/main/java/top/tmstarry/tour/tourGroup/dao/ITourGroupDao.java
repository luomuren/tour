package top.tmstarry.tour.tourGroup.dao;

import org.apache.ibatis.annotations.Mapper;
import top.tmstarry.tour.base.dao.IBaseDao;
import top.tmstarry.tour.tourGroup.service.bean.TourGroup;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ITourGroupDao
 * @Author 落幕人
 * <p>
 * 旅游团Dao
 * <p>
 * @Date 2019/11/6 11:05
 * @Version 1.0
 **/
@Mapper
public interface ITourGroupDao extends IBaseDao<TourGroup> {
    public List<TourGroup> getTourByScenic(int id);

    public List<String> getScenicImgByTour(int id);

    public void upTourNum(int id);

    public void joinTour(Map<String, Object> map);

    public TourGroup getIsJoinTour(Map<String, Object> map);
}
