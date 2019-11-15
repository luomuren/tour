package top.tmstarry.tour.tourGroup.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.tmstarry.tour.base.service.bean.DataGridResult;
import top.tmstarry.tour.base.view.BaseController;
import top.tmstarry.tour.comment.service.bean.Comment;
import top.tmstarry.tour.scenicSpot.service.bean.ScenicSpot;
import top.tmstarry.tour.tourGroup.service.ITourGroupService;
import top.tmstarry.tour.tourGroup.service.bean.TourGroup;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName TourGroupController
 * @Author 落幕人
 * <p>
 * 旅游团控制器
 * <p>
 * @Date 2019/11/6 11:07
 * @Version 1.0
 **/
@RestController
@RequestMapping("/tourGroup")
public class TourGroupController extends BaseController<ITourGroupService, TourGroup> {
    private static final Logger logger = LogManager.getLogger(TourGroupController.class);

    @RequestMapping("/up")
    public TourGroup upTour(@RequestParam("upimg") MultipartFile upimg, Integer id) {
        String headName = upimg.getOriginalFilename();
        String substring = headName.substring(headName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        String headImgName = uuid + substring;
        TourGroup bean = new TourGroup();
        bean.setTourGroupImg(headImgName);
        try {
            File file = new File(ResourceUtils.getURL("classpath:").getPath());
            File file2 = new File(file.getAbsoluteFile() + "/static/upload");
            if (!file2.exists()) {
                file2.mkdir();
            }
            upimg.transferTo(new File(file2 + "/" + headImgName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        service.update(bean, id);
        return bean;
    }

    @Override
    public TourGroup add(TourGroup bean, HttpSession session) {
        return super.add(bean,session);
    }

    /**
     * 非继承批量添加
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/addBatch", method = RequestMethod.POST)
    public boolean addBatch(String bean,String chufaTime, HttpSession session) {
        String[] split1 = chufaTime.split(",");
        Date[] chufaTimes = new Date[split1.length];
        for (int i = 0; i < split1.length; i++) {
            chufaTimes[i] =new Date(split1[i]);
        }

        if ("".equals(bean)) {
            return false;
        }
        Gson gson = new Gson();
        ArrayList<TourGroup> beans = gson.fromJson(bean, new TypeToken<List<TourGroup>>() {
        }.getType());
        for (int i = 0; i < beans.size(); i++) {
            TourGroup b = beans.get(i);
            b.setChufaTime(chufaTimes[i]);
            b.setCreatTime(new Date());
            b.setUpTime(b.getCreatTime());
            b.setCreatUser((String) session.getAttribute("userName"));
            b.setUpUser(b.getCreatUser());
        }
        service.addBatch(beans);
        return true;
    }

    @Override
    public TourGroup delete(int id) {
        return super.delete(id);
    }

    @Override
    public boolean deleteALl(String ids) {
        return super.deleteALl(ids);
    }

    @Override
    public TourGroup update(TourGroup bean, int id, HttpSession session) {
        return super.update(bean, id,session);
    }

    /**
     * 非继承自定义批量修改
     *
     * @param bean
     * @param ids
     * @return
     */
    @RequestMapping(value = "/upBatch", method = RequestMethod.PUT)
    public boolean upPatch(String bean, String ids,String chufaTime, HttpSession session) {
        if (ids.equals("")) {
            return false;
        }

        String[] split1 = chufaTime.split(",");
        Date[] chufaTimes = new Date[split1.length];
        for (int i = 0; i < split1.length; i++) {
            chufaTimes[i] =new Date(split1[i]);
        }

        Gson gson = new Gson();
        ArrayList<TourGroup> beans = gson.fromJson(bean, new TypeToken<List<TourGroup>>() {
        }.getType());
        for (int i = 0; i < beans.size(); i++) {

            TourGroup b = beans.get(i);
            b.setChufaTime(chufaTimes[i]);
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
    public TourGroup get(int id) {
        return super.get(id);
    }

    @Override
    public DataGridResult getListByPage(Map<String, Object> map) {
        return super.getListByPage(map);
    }

    @RequestMapping("/joinTour")
    public boolean joinTour(Integer id,HttpSession session) {
        if (session.getAttribute("userName") != null) {
            service.upTourNum(id);
            Map<String, Object> map = new HashMap<>();
            int userId = (int) session.getAttribute("userId");
            map.put("userId", userId);
            map.put("tourId", id);
            service.joinTour(map);
            return true;
        } else {
            return false;
        }
    }
}
