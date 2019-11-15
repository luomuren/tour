package top.tmstarry.tour.line.dao;

import org.apache.ibatis.annotations.Mapper;
import top.tmstarry.tour.line.service.bean.Line;
import top.tmstarry.tour.scenicSpot.service.bean.ScenicSpot;

import java.util.List;
import java.util.Map;

@Mapper
public interface ILineDao{
    public int insert(Line line);

    public void update(Line line);

    public void delete(int[] ids);

    public List<Line> getList(Map<String, ? extends Object> map);

    public Line getOne(Map<String, ? extends Object> map);

    public void insertLineScenic(Map<String, Object> map);

    public void deleteLineScenic(int[] ids);

    public List<ScenicSpot> getLineScenic(Map<String, ? extends Object> map);

    public void addLs(Map<String, Object> map);

}
