package top.tmstarry.tour.comment.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.tmstarry.tour.base.service.bean.DataGridResult;
import top.tmstarry.tour.base.view.BaseController;
import top.tmstarry.tour.comment.service.ICommentService;
import top.tmstarry.tour.comment.service.bean.Comment;
import top.tmstarry.tour.power.service.bean.Power;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @ClassName CommentController
 * @Author 落幕人
 * <p>
 * Comment控制类
 * <p>
 * @Date 2019/11/4 23:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController<ICommentService, Comment> {
    private static final Logger logger = LogManager.getLogger(CommentController.class);

    @Override
    public Comment add(Comment bean, HttpSession session) {
        return super.add(bean,session);
    }

    //添加景点评论
    @RequestMapping("/addComSc")
    public Comment addComSc(Comment comment, Integer scenicId, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return null;
        } else {
            int userId = (int) session.getAttribute("userId");
            String userName = (String) session.getAttribute("userName");
            comment.setCommentUser(userId);
            comment.setCreatTime(new Date());
            comment.setUpTime(comment.getCreatTime());
            comment.setCreatUser(userName);
            comment.setUpUser(comment.getCreatUser());
            service.add(comment);
            int commentId = comment.getCommentId();
            Map<String, Object> map = new HashMap<>();
            map.put("commentId",commentId);
            map.put("scenicId", scenicId);
            service.addCommentScenic(map);
            return comment;
        }
    }

    //添加旅游团评论
    @RequestMapping("/addComTour")
    public Comment addComTour(Comment comment, Integer tourId, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return null;
        } else {
            int userId = (int) session.getAttribute("userId");
            String userName = (String) session.getAttribute("userName");
            comment.setCommentUser(userId);
            comment.setCreatTime(new Date());
            comment.setUpTime(comment.getCreatTime());
            comment.setCreatUser(userName);
            comment.setUpUser(comment.getCreatUser());
            service.add(comment);
            int commentId = comment.getCommentId();
            Map<String, Object> map = new HashMap<>();
            map.put("commentId", commentId);
            map.put("tourId", tourId);
            service.addCommentTour(map);
            return comment;
        }
    }


    /**
     * 非继承批量添加
     * @param bean
     * @return
     */
    @RequestMapping(value = "/addBatch",method = RequestMethod.POST)
    public boolean addBatch(String bean,HttpSession session) {
        if ("".equals(bean)) {
            return false;
        }
        Gson gson = new Gson();
        ArrayList<Comment> beans = gson.fromJson(bean, new TypeToken<List<Comment>>() {
        }.getType());
        Iterator<Comment> iterator = beans.iterator();
        while (iterator.hasNext()) {
            Comment b = iterator.next();
            b.setCreatTime(new Date());
            b.setUpTime(b.getCreatTime());
            b.setCreatUser((String) session.getAttribute("userName"));
            b.setUpUser(b.getCreatUser());
        }

        service.addBatch(beans);
        return true;
    }

    @Override
    public Comment delete(int id) {
        return super.delete(id);
    }

    @Override
    public boolean deleteALl(String ids) {
        return super.deleteALl(ids);
    }

    @Override
    public Comment update(Comment bean, int id, HttpSession session) {
        return super.update(bean, id,session);
    }

    /**
     * 非继承自定义批量修改
     * @param bean
     * @param ids
     * @return
     */
    @RequestMapping(value = "/upBatch",method = RequestMethod.PUT)
    public boolean upPatch(String bean, String ids,HttpSession session) {
        if (ids.equals("")) {
            return false;
        }

        Gson gson = new Gson();
        ArrayList<Comment> beans = gson.fromJson(bean, new TypeToken<List<Comment>>() {
        }.getType());
        Iterator<Comment> iterator = beans.iterator();
        while (iterator.hasNext()) {
            Comment b = iterator.next();
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
    public Comment get(int id) {
        return super.get(id);
    }

    @Override
    public DataGridResult getListByPage(Map<String, Object> map) {
        return super.getListByPage(map);
    }
}
