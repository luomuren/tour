package top.tmstarry.tour.scenicSpot.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.tmstarry.tour.base.service.bean.DataGridResult;
import top.tmstarry.tour.base.view.BaseController;
import top.tmstarry.tour.power.service.bean.Power;
import top.tmstarry.tour.scenicSpot.service.IScenicSpotClassService;
import top.tmstarry.tour.scenicSpot.service.bean.ScenicSpotClass;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @ClassName ScenicSpotClassController
 * @Author 落幕人
 * <p>
 * 景区分类控制器
 * <p>
 * @Date 2019/11/5 10:51
 * @Version 1.0
 **/
@RestController
@RequestMapping("/scenicSpotClass")
public class ScenicSpotClassController extends BaseController<IScenicSpotClassService, ScenicSpotClass> {
    private static final Logger logger = LogManager.getLogger(ScenicSpotClassController.class);

    @Override
    public ScenicSpotClass add(ScenicSpotClass bean, HttpSession session) {
        return super.add(bean,session);
    }

    /**
     * 非继承批量添加
     * @param bean
     * @return
     */
    @RequestMapping(value = "/addBatch",method = RequestMethod.POST)
    public boolean addBatch(String bean, HttpSession session) {
        if ("".equals(bean)) {
            return false;
        }
        Gson gson = new Gson();
        ArrayList<ScenicSpotClass> beans = gson.fromJson(bean, new TypeToken<List<ScenicSpotClass>>() {
        }.getType());
        Iterator<ScenicSpotClass> iterator = beans.iterator();
        while (iterator.hasNext()) {
            ScenicSpotClass b = iterator.next();
            b.setCreatTime(new Date());
            b.setUpTime(b.getCreatTime());
            b.setCreatUser((String) session.getAttribute("userName"));
            b.setUpUser(b.getCreatUser());
        }

        service.addBatch(beans);
        return true;
    }

    @Override
    public ScenicSpotClass delete(int id) {
        return super.delete(id);
    }

    @Override
    public boolean deleteALl(String ids) {
        return super.deleteALl(ids);
    }

    @Override
    public ScenicSpotClass update(ScenicSpotClass bean, int id, HttpSession session) {
        return super.update(bean, id,session);
    }

    /**
     * 非继承自定义批量修改
     * @param bean
     * @param ids
     * @return
     */
    @RequestMapping(value = "/upBatch",method = RequestMethod.PUT)
    public boolean upPatch(String bean, String ids, HttpSession session) {
        if (ids.equals("")) {
            return false;
        }

        Gson gson = new Gson();
        ArrayList<ScenicSpotClass> beans = gson.fromJson(bean, new TypeToken<List<ScenicSpotClass>>() {
        }.getType());
        Iterator<ScenicSpotClass> iterator = beans.iterator();
        while (iterator.hasNext()) {
            ScenicSpotClass b = iterator.next();
            b.setUpTime(new Date());
            b.setUpUser((String) session.getAttribute("userName"));
        }

        String[] split = ids.split(",");
        int[] idss = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            idss[i] = Integer.parseInt(split[i]);
        }
        service.updateBatch(beans, idss);
        return true;
    }


    @Override
    public ScenicSpotClass get(int id) {
        return super.get(id);
    }

    @Override
    public DataGridResult getListByPage(Map<String, Object> map) {
        DataGridResult listByPage = super.getListByPage(map);
        return listByPage;
    }

    @RequestMapping("/getList")
    public List<ScenicSpotClass> getList() {
        DataGridResult itemByPage = service.findItemByPage(new HashMap<>());
        List<ScenicSpotClass> rows = (List<ScenicSpotClass>) itemByPage.getRows();
        return rows;

    }
}
