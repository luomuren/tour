package top.tmstarry.tour.line.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.tmstarry.tour.base.service.bean.DataGridResult;
import top.tmstarry.tour.line.service.ILineService;
import top.tmstarry.tour.line.service.bean.Line;
import top.tmstarry.tour.scenicSpot.service.bean.ScenicSpot;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/line")
public class LineController{
    private static final Logger logger = LogManager.getLogger(LineController.class);

    @Autowired
    private ILineService lineService;

    @RequestMapping(method = RequestMethod.POST)
    public boolean add(Line line,String ids, HttpSession session) {
        String[] split = ids.split(",");
        int[] idss = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            idss[i] = Integer.parseInt(split[i]);
        }
        lineService.insert(line,session);
        lineService.insertLineScenic(idss,line.getLineId());
        return true;
    }

    @RequestMapping("/addLs")
    public boolean addLs(Integer lineId, String ids) {
        String[] split = ids.split(",");
        int[] idss = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            idss[i] = Integer.parseInt(split[i]);
        }
        lineService.addLs(lineId,idss);
        return true;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public boolean update(Line line, String ids) {
        String[] split = ids.split(",");
        int[] idss = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            idss[i] = Integer.parseInt(split[i]);
        }
        lineService.update(line);
        lineService.deleteLineScenic(idss);
        lineService.insertLineScenic(idss,line.getLineId());
        return true;
    }

    /**
     * 非继承自定义批量修改
     * @param bean
     * @return
     */
    @RequestMapping(value = "/upBatch",method = RequestMethod.PUT)
    public boolean upPatch(String bean,String ids, HttpSession session) {
        logger.info(ids);
        String[] split = ids.split(",");
        int[] idss = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            idss[i] = Integer.parseInt(split[i]);
        }
        Gson gson = new Gson();
        ArrayList<Line> beans = gson.fromJson(bean, new TypeToken<List<Line>>() {
        }.getType());
        Iterator<Line> iterator = beans.iterator();
        while (iterator.hasNext()) {
            Line b = iterator.next();
            b.setUpTime(new Date());
            b.setUpUser((String) session.getAttribute("userName"));
        }
        lineService.updateBatch(beans,idss);
        return true;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public boolean delete(String ids) {
        String[] split = ids.split(",");
        int[] idss = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            idss[i] = Integer.parseInt(split[i]);
        }
        lineService.delete(idss);
        lineService.deleteLineScenic(idss);
        return true;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Line getLine(@PathVariable("id") int id) {
        Map<String, Integer> map = new HashMap() {{
            put("lineId", id);
        }};
        Line one = lineService.getOne(map);
        return one;
    }

    @RequestMapping(value = "/getls/{id}",method = RequestMethod.GET)
    public List<ScenicSpot> getLS(@PathVariable("id") int id) {
        Map<String, Integer> map = new HashMap() {{
            put("lineId", id);
        }};
        List<ScenicSpot> lineScenic = lineService.getLineScenic(map);
        return lineScenic;
    }

    @RequestMapping(method = RequestMethod.GET)
    public DataGridResult getList(@RequestParam Map<String, Object> map) {
        List<Line> list = lineService.getList(map);
        DataGridResult result = new DataGridResult();
        result.setRows(list);
        return result;
    }

    @RequestMapping("/getList")
    public List<Line> getLineList() {
        Map<String, Object> map = new HashMap<>();
        List<Line> list = lineService.getList(map);
        return list;
    }

}
