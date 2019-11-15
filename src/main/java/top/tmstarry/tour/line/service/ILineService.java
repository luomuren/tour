package top.tmstarry.tour.line.service;

import top.tmstarry.tour.line.service.bean.Line;
import top.tmstarry.tour.scenicSpot.service.bean.ScenicSpot;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface ILineService{
    public int insert(Line line, HttpSession session);

    public void update(Line line);

    public void updateBatch(List<Line> beans,int[] ids);

    public void delete(int[] ids);

    public List<Line> getList(Map<String, ? extends Object> map);

    public Line getOne(Map<String, ? extends Object> map);

    public void insertLineScenic(int[] idss,int lineId);

    public void deleteLineScenic(int[] ids);

    public List<ScenicSpot> getLineScenic(Map<String, ? extends Object> map);

    public void addLs(Integer lineId, int[] ids);
}
