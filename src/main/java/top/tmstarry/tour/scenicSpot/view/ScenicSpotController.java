package top.tmstarry.tour.scenicSpot.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.ibatis.io.Resources;
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
import top.tmstarry.tour.scenicSpot.service.IScenicSpotService;
import top.tmstarry.tour.scenicSpot.service.bean.ScenicSpot;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @ClassName ScenicSpotController
 * @Author 落幕人
 * <p>
 * 景区控制器
 * <p>
 * @Date 2019/11/5 10:50
 * @Version 1.0
 **/
@RestController
@RequestMapping("/scenicSpot")
public class ScenicSpotController extends BaseController<IScenicSpotService, ScenicSpot> {
    private static final Logger logger = LogManager.getLogger(ScenicSpotController.class);

    @RequestMapping("/up")
    public ScenicSpot upScenicSpot(@RequestParam("upimg") MultipartFile upimg,Integer id) {
        String headName = upimg.getOriginalFilename();
        String substring = headName.substring(headName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        String headImgName = uuid + substring;
        ScenicSpot bean = new ScenicSpot();
        bean.setScenicSpotImg(headImgName);
        try {
            File  file=new File(ResourceUtils.getURL("classpath:").getPath());
            File file2=new File(file.getAbsoluteFile()+"/static/upload");
            if (!file2.exists()){
                file2.mkdir();
            }
            upimg.transferTo(new File(file2+"/"+headImgName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        service.update(bean, id);
        return bean;
    }

    @RequestMapping("/add")
    public ScenicSpot addScenicSpot(ScenicSpot bean, @RequestParam("img") MultipartFile img, HttpSession session) {
        String headName = img.getOriginalFilename();
        String substring = headName.substring(headName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        String headImgName = uuid + substring;
        bean.setScenicSpotImg(headImgName);
        try {
            File  file=new File(ResourceUtils.getURL("classpath:").getPath());
            File file2=new File(file.getAbsoluteFile()+"/static/upload");
            if (!file2.exists()){
                file2.mkdir();
            }
            img.transferTo(new File(file2+"/"+headImgName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bean.setCreatTime(new Date());
        bean.setUpTime(bean.getCreatTime());
        bean.setCreatUser((String) session.getAttribute("userName"));
        bean.setUpUser(bean.getCreatUser());
        service.add(bean);
        return bean;
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
        ArrayList<ScenicSpot> beans = gson.fromJson(bean, new TypeToken<List<ScenicSpot>>() {
        }.getType());
        Iterator<ScenicSpot> iterator = beans.iterator();
        while (iterator.hasNext()) {
            ScenicSpot b = iterator.next();
            b.setCreatTime(new Date());
            b.setUpTime(b.getCreatTime());
            b.setCreatUser((String) session.getAttribute("userName"));
            b.setUpUser(b.getCreatUser());
        }

        service.addBatch(beans);
        return true;
    }

    @Override
    public ScenicSpot delete(int id) {
        return super.delete(id);
    }

    @Override
    public boolean deleteALl(String ids) {
        return super.deleteALl(ids);
    }

    @Override
    public ScenicSpot update(ScenicSpot bean, int id, HttpSession session) {
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
        ArrayList<ScenicSpot> beans = gson.fromJson(bean, new TypeToken<List<ScenicSpot>>() {
        }.getType());
        Iterator<ScenicSpot> iterator = beans.iterator();
        while (iterator.hasNext()) {
            ScenicSpot b = iterator.next();
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
    public ScenicSpot get(int id) {
        return super.get(id);
    }

    @Override
    public DataGridResult getListByPage(Map<String, Object> map) {
        return super.getListByPage(map);
    }

}
