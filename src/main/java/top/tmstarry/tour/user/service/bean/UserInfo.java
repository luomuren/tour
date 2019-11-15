package top.tmstarry.tour.user.service.bean;

import top.tmstarry.tour.base.service.bean.Base;

/**
 * @ClassName UserInfo
 * @Author 落幕人
 * <p>
 * 用户详情实体
 * <p>
 * @Date 2019/11/4 20:10
 * @Version 1.0
 **/
public class UserInfo extends Base {
    private int userId;
    private String userName;
    private String realName;
    private String sex;
    private String QQ;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }
}
