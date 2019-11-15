package top.tmstarry.tour.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.tmstarry.tour.base.dao.IBaseDao;
import top.tmstarry.tour.user.service.bean.User;
import top.tmstarry.tour.user.service.bean.UserInfo;

import java.util.List;
import java.util.Set;

@Mapper
public interface IUserDao extends IBaseDao<User> {

    public User getUser(String userName);

    public Set<String> selectRoles(String userName);

    public Set<String> selectPermissions(String userName);

    public void insertUserInfo(UserInfo userInfo);

    public List<User> getGuide();

    public List<UserInfo> getAGuide(int id);
}
