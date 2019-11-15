package top.tmstarry.tour.scenicSpot.dao;

import org.apache.ibatis.annotations.Mapper;
import top.tmstarry.tour.base.dao.IBaseDao;
import top.tmstarry.tour.scenicSpot.service.bean.ScenicSpotClass;

/**
 * @ClassName IScenicSpotClassDao
 * @Author 落幕人
 * <p>
 * ScenicSpotClassDao
 * <p>
 * @Date 2019/11/5 10:08
 * @Version 1.0
 **/
@Mapper
public interface IScenicSpotClassDao extends IBaseDao<ScenicSpotClass> {
}
