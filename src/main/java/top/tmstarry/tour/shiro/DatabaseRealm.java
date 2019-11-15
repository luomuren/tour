package top.tmstarry.tour.shiro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import top.tmstarry.tour.user.service.IUserService;
import top.tmstarry.tour.user.service.bean.User;

public class DatabaseRealm extends AuthorizingRealm {
    private static final Logger logger = LogManager.getLogger(DatabaseRealm.class);
    @Autowired
    private IUserService userService;

    public static String salf = "123456";

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorization = new SimpleAuthorizationInfo();
        authorization.setRoles(userService.selectRoles(userName));
        authorization.setStringPermissions(userService.selectPermissions(userName));
        return authorization;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getPrincipal().toString();
        if (userName == null) {
            throw new AccountException("用户名不能未空");
        }
        User user = userService.getUser(userName);
        if (user==null) {
            throw new UnknownAccountException("用户名或密码错误");
        }
        SimpleAuthenticationInfo authentication = new SimpleAuthenticationInfo(userName, user.getPassword(), getName());
        authentication.setCredentialsSalt(ByteSource.Util.bytes(DatabaseRealm.salf));
        return authentication;
    }
}
