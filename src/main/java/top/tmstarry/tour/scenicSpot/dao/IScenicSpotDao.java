package top.tmstarry.tour.scenicSpot.dao;

import org.apache.ibatis.annotations.Mapper;
import top.tmstarry.tour.base.dao.IBaseDao;
import top.tmstarry.tour.scenicSpot.service.bean.ScenicSpot;

/**
 * @ClassName IScenicSpotDao
 * @Author 落幕人
 * <p>
 * ScenicSpotDao
 * <p>
 * @Date 2019/11/5 10:07
 * @Version 1.0
 **/
@Mapper
public interface IScenicSpotDao extends IBaseDao<ScenicSpot> {
}
