package top.tmstarry.tour.shiro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import top.tmstarry.tour.base.service.bean.DataGridResult;
import top.tmstarry.tour.comment.service.ICommentService;
import top.tmstarry.tour.comment.service.bean.Comment;
import top.tmstarry.tour.scenicSpot.service.IScenicSpotService;
import top.tmstarry.tour.scenicSpot.service.bean.ScenicSpot;
import top.tmstarry.tour.tourGroup.service.ITourGroupService;
import top.tmstarry.tour.tourGroup.service.bean.TourGroup;
import top.tmstarry.tour.user.service.IUserService;
import top.tmstarry.tour.user.service.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ViewController
 * @Author 落幕人
 * <p>
 * 视图控制器，控制跳转视图
 * <p>
 * @Date 2019/11/1 19:19
 * @Version 1.0
 **/
@Controller
public class ViewController {
    private static final Logger logger = LogManager.getLogger(ViewController.class);

    @Autowired
    private ITourGroupService tourGroupService;
    @Autowired
    private IScenicSpotService iScenicSpotService;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IUserService userService;

    //    @RequiresUser
    @RequestMapping("/")
    public String toIndex(Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("startIndex", 0);
        map.put("rows", 3);
        DataGridResult itemByPage = tourGroupService.findItemByPage(map);
        model.addAttribute("tourGroups", itemByPage.getRows());
        return "index";
    }

    /**
     * 跳转登录页
     *
     * @return
     */
    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }

    //跳转前端页面
    @RequestMapping("/about")
    public String toAbout() {
        return "frontEnd/about";
    }

    @RequestMapping("/codes")
    public String toCodes() {
        return "frontEnd/codes";
    }

    @RequestMapping("/services")
    public String toService() {
        return "frontEnd/services";
    }

    @RequestMapping("/toScenicSpotInfo")
    public String toScenicSpotInfo(Integer id, String type, Model model, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        if ("scenic".equals(type)) {
            ScenicSpot scenicSpot = iScenicSpotService.get(map);
            model.addAttribute("bean", scenicSpot);
            List<Comment> commentByScenic = commentService.getCommentByScenic(id);
            model.addAttribute("comment", commentByScenic);
            model.addAttribute("commentCount", commentByScenic.size());
            Map<String, Object> map1 = new HashMap<>();
            map.put("startIndex", 0);
            map.put("rows", 8);
            map.put("sort", "creat_time");
            map.put("order", "desc");
            //获取8条景点显示
            DataGridResult itemByPage = iScenicSpotService.findItemByPage(map);
            model.addAttribute("otherScenic", itemByPage.getRows());
            //获取相关旅游团显示
            List<TourGroup> tourByScenic = tourGroupService.getTourByScenic(id);
            model.addAttribute("tours", tourByScenic);
            return "frontEnd/single";
        } else if ("tour".equals(type)){
            TourGroup tourGroup = tourGroupService.get(map);
            //旅游团信息
            model.addAttribute("bean", tourGroup);
            List<Comment> commentByTour = commentService.getCommentByTour(id);
            //评论信息
            model.addAttribute("comment", commentByTour);
            model.addAttribute("commentCount", commentByTour.size());

            //获取景点图
            List<String> scenicImgByTour = tourGroupService.getScenicImgByTour(id);
            model.addAttribute("imgs", scenicImgByTour);

            //获取导游详情
            User user = userService.getUser(tourGroup.getUserName());
            model.addAttribute("guide", user);

            if (session.getAttribute("userId") != null) {
                //判断是否已加入该旅游团
                Map<String, Object> map1 = new HashMap<>();
                map.put("userId", session.getAttribute("userId"));
                map.put("tourId", id);
                boolean isJoinTour = tourGroupService.getIsJoinTour(map);
                model.addAttribute("isJoin", isJoinTour);
            }
            return "frontEnd/singleTour";
        }
        return "404";
    }

    @RequestMapping("/gallery")
    public String toGallery(Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("startIndex", 0);
        map.put("rows", 16);
        map.put("sort", "creat_time");
        map.put("order", "desc");
        DataGridResult itemByPage = iScenicSpotService.findItemByPage(map);
        List<ScenicSpot> rows = (List<ScenicSpot>) itemByPage.getRows();
        model.addAttribute("scenicSpots", rows);
        return "frontEnd/gallery";
    }
    //END-前端页面

    //跳转后台页面
    @RequiresUser
    @RequiresRoles(value = {"manage"})
    @RequestMapping("/manage")
    public String toManage() {
        return "manage/manage";
    }

    @RequiresRoles(value = {"管理员"})
    @RequestMapping("/user_manage")
    public String toUserManage() {
        return "templates/usertemp/user_manage";
    }

    @RequiresRoles(value = {"管理员"})
    @RequestMapping("/role_manage")
    public String toRoleManage() {
        return "templates/usertemp/role_manage";
    }

    @RequiresRoles(value = {"管理员"})
    @RequestMapping("/power_manage")
    public String toPowerManage() {
        return "templates/usertemp/power_manage";
    }

    @RequiresRoles(value = {"管理员"})
    @RequestMapping("/comment_manage")
    public String toCommentManage() {
        return "templates/comment/comment_manage";
    }

    @RequiresRoles(value = {"导游"})
    @RequestMapping("/ssclass_manage")
    public String toSsClassManage() {
        return "templates/scenicSpot/class_manage";
    }

    @RequiresRoles(value = {"导游"})
    @RequestMapping("/scenicSpot_manage")
    public String toScenicSpotManage() {
        return "templates/scenicSpot/scenicSpot_manage";
    }

    @RequiresRoles(value = {"导游"})
    @RequestMapping("/line_manage")
    public String toLineManage() {
        return "templates/scenicSpot/line_manage";
    }

    @RequiresRoles(value = {"导游"})
    @RequestMapping("/tourGroup_manage")
    public String toTourGroup() {
        return "/templates/scenicSpot/tourGroup_manage";
    }


    //错误跳转
    @RequestMapping("/500")
    public String to500() {
        return "error/500.html";
    }
    //END-错误

}
