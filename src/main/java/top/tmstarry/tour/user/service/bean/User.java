package top.tmstarry.tour.user.service.bean;

import top.tmstarry.tour.base.service.bean.Base;

import java.util.Date;

public class User extends Base {
    private int userId;
    private String userName;
    private String password;
    private String email;
    private UserInfo userInfo;
    private int guide;

    public int getGuide() {
        return guide;
    }

    public void setGuide(int guide) {
        this.guide = guide;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
