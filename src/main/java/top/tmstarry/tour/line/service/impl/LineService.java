package top.tmstarry.tour.line.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tmstarry.tour.line.dao.ILineDao;
import top.tmstarry.tour.line.service.ILineService;
import top.tmstarry.tour.line.service.bean.Line;
import top.tmstarry.tour.scenicSpot.service.bean.ScenicSpot;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LineService implements ILineService {
    @Autowired
    private ILineDao lineDao;

    @Override
    public int insert(Line line, HttpSession session) {
        line.setCreatTime(new Date());
        line.setUpTime(line.getCreatTime());
        line.setCreatUser((String) session.getAttribute("userName"));
        line.setUpUser(line.getCreatUser());
        return lineDao.insert(line);
    }

    @Override
    public void update(Line line) {
        lineDao.update(line);
    }

    @Override
    public void updateBatch(List<Line> beans,int[] ids){
        for (int i = 0; i < beans.size(); i++) {
            beans.get(i).setLineId(ids[i]);
            lineDao.update(beans.get(i));
        }
    }

    @Override
    public void delete(int[] ids) {
        lineDao.delete(ids);
    }

    @Override
    public List<Line> getList(Map<String, ?> map) {
        return lineDao.getList(map);
    }

    @Override
    public Line getOne(Map<String, ?> map) {
        return lineDao.getOne(map);
    }

    @Override
    public void insertLineScenic(int[] idss,int lineId) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < idss.length; i++) {
            map.put("scenicsId", idss[i]);
            map.put("lineId", lineId);
            lineDao.insertLineScenic(map);
        }
    }

    @Override
    public void deleteLineScenic(int[] ids) {
        lineDao.deleteLineScenic(ids);
    }

    @Override
    public List<ScenicSpot> getLineScenic(Map<String, ?> map) {
        return lineDao.getLineScenic(map);
    }

    @Override
    public void addLs(Integer lineId, int[] ids) {
        lineDao.deleteLineScenic(new int[]{lineId});
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < ids.length; i++) {
            map.put("lineId", lineId);
            map.put("sId", ids[i]);
            lineDao.addLs(map);
        }
    }
}
