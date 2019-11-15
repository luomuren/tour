package top.tmstarry.tour.comment.service.bean;

import top.tmstarry.tour.base.service.bean.Base;

import java.util.Date;

/**
 * @ClassName Comment
 * @Author 落幕人
 * <p>
 * Comment实体类
 * <p>
 * @Date 2019/11/4 23:09
 * @Version 1.0
 **/
public class Comment extends Base {
    private int commentId;
    private int commentUser;
    private String commentContent;
    private String userName;

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentUser=" + commentUser +
                ", commentContent='" + commentContent + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public Date getCreatTime() {
        return super.getCreatTime();
    }

    @Override
    public void setCreatTime(Date creatTime) {
        super.setCreatTime(creatTime);
    }

    @Override
    public Date getUpTime() {
        return super.getUpTime();
    }

    @Override
    public void setUpTime(Date upTime) {
        super.setUpTime(upTime);
    }

    @Override
    public String getCreatUser() {
        return super.getCreatUser();
    }

    @Override
    public void setCreatUser(String creatUser) {
        super.setCreatUser(creatUser);
    }

    @Override
    public String getUpUser() {
        return super.getUpUser();
    }

    @Override
    public void setUpUser(String upUser) {
        super.setUpUser(upUser);
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(int commentUser) {
        this.commentUser = commentUser;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
