package top.tmstarry.tour.comment.service.impl;

import org.springframework.stereotype.Service;
import top.tmstarry.tour.base.service.impl.BaseService;
import top.tmstarry.tour.comment.dao.ICommentDao;
import top.tmstarry.tour.comment.service.ICommentService;
import top.tmstarry.tour.comment.service.bean.Comment;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CommentService
 * @Author 落幕人
 * <p>
 * CommentService实现类
 * <p>
 * @Date 2019/11/4 23:12
 * @Version 1.0
 **/
@Service
public class CommentService extends BaseService<ICommentDao, Comment> implements ICommentService {

    @Override
    public List<Comment> getCommentByScenic(int id) {
        return dao.getCommentByScenic(id);
    }

    @Override
    public List<Comment> getCommentByTour(int id) {
        return dao.getCommentByTour(id);
    }

    @Override
    public void addCommentScenic(Map<String, Object> map) {
        dao.addCommentScenic(map);
    }

    @Override
    public void addCommentTour(Map<String, Object> map) {
        dao.addCommentTour(map);
    }
}
