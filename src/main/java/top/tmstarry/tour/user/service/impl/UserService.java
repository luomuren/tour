package top.tmstarry.tour.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tmstarry.tour.base.service.impl.BaseService;
import top.tmstarry.tour.user.dao.IUserDao;
import top.tmstarry.tour.user.service.IUserService;
import top.tmstarry.tour.user.service.bean.User;
import top.tmstarry.tour.user.service.bean.UserInfo;

import java.util.List;
import java.util.Set;

@Service
public class UserService extends BaseService<IUserDao,User> implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public User getUser(String userName) {
        User user = userDao.getUser(userName);
        return user;
    }

    @Override
    public Set<String> selectRoles(String userName) {
        return userDao.selectRoles(userName);
    }

    @Override
    public Set<String> selectPermissions(String userName) {
        return userDao.selectPermissions(userName);
    }

    @Override
    public void insertUserInfo(UserInfo userInfo) {
        userDao.insertUserInfo(userInfo);
    }

    @Override
    public List<User> getGuide() {
        return userDao.getGuide();
    }

    @Override
    public List<UserInfo> getAGuide(int id) {
        return userDao.getAGuide(id);
    }
}
