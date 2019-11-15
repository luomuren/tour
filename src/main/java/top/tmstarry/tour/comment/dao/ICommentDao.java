package top.tmstarry.tour.comment.dao;

import org.apache.ibatis.annotations.Mapper;
import top.tmstarry.tour.base.dao.IBaseDao;
import top.tmstarry.tour.comment.service.bean.Comment;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ICommentDao
 * @Author 落幕人
 * <p>
 * CommentDao
 * <p>
 * @Date 2019/11/4 23:11
 * @Version 1.0
 **/
@Mapper
public interface ICommentDao extends IBaseDao<Comment> {
    public List<Comment> getCommentByScenic(int id);

    public List<Comment> getCommentByTour(int id);

    public void addCommentScenic(Map<String, Object> map);

    public void addCommentTour(Map<String, Object> map);

}
