package top.tmstarry.tour.comment.service;

import top.tmstarry.tour.base.service.IBaseService;
import top.tmstarry.tour.comment.service.bean.Comment;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ICommentService
 * @Author 落幕人
 * <p>
 * CommentService接口
 * <p>
 * @Date 2019/11/4 23:12
 * @Version 1.0
 **/
public interface ICommentService extends IBaseService<Comment> {
    public List<Comment> getCommentByScenic(int id);

    public List<Comment> getCommentByTour(int id);

    public void addCommentScenic(Map<String, Object> map);

    public void addCommentTour(Map<String, Object> map);
}
