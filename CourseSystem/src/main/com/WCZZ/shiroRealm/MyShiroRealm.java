package main.com.WCZZ.shiroRealm;

import main.com.WCZZ.entity.Role;
import main.com.WCZZ.entity.User;
import main.com.WCZZ.service.PermissionService;
import main.com.WCZZ.service.RoleService;
import main.com.WCZZ.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    @Qualifier("permissionService")
    private PermissionService permissionService;
    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录时输入的用户名
        String userId=(String) principalCollection.fromRealm(getName()).iterator().next();
        if(userId!=null){
            List<String> listUrl=permissionService.getTheUrl(userId);//权限
            User user=userService.getUserByUserId(userId);//用户信息
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            if(listUrl!=null&&!listUrl.isEmpty()){
                for(String url:listUrl){
                    info.addStringPermission(url);//加入权限
                }
            }
            List<Role> roles=roleService.getRoles(user.getUserId());
            if(roles!=null&&!roles.isEmpty()){
                for(Role role:roles){
                    info.addRole(role.getName());//加入角色
                }
            }
            return info;
        }
        return null;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        //通过表单接收的用户名
        String userId=token.getUsername();
        if(userId!=null&&!"".equals(userId)){
            User user=userService.getUserByUserId(userId);
            if(user!=null){
                return new SimpleAuthenticationInfo(user.getUserId(),user.getPassword(),getName());
            }
        }
        return null;
    }
}