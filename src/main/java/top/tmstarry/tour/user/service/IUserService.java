package top.tmstarry.tour.user.service;

import top.tmstarry.tour.base.service.IBaseService;
import top.tmstarry.tour.user.service.bean.User;
import top.tmstarry.tour.user.service.bean.UserInfo;

import java.util.List;
import java.util.Set;

public interface IUserService extends IBaseService<User> {
    public User getUser(String userName);

    public Set<String> selectRoles(String userName);

    public Set<String> selectPermissions(String userName);

    public void insertUserInfo(UserInfo userInfo);

    public List<User> getGuide();

    public List<UserInfo> getAGuide(int id);
}
