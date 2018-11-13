package main.com.WCZZ.util;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.http.HttpStatus;

/**
 * 通过角色验证权限
 * @author chenyd
 * 2017年11月21日
 */
public class ExtendRolesAuthorizationFilter extends RolesAuthorizationFilter{

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        if (request instanceof HttpServletRequest) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
                System.out.println(SecurityUtils.getSubject().getSession().getId());
                httpResponse.setStatus(HttpStatus.OK.value());
                return true;
            }
        }
        System.out.println(ExtendRolesAuthorizationFilter.class.toString());
        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;

        if (rolesArray == null || rolesArray.length == 0) {
            //no roles specified, so nothing to check - allow access.
            return true;
        }
        //AbstractFilter
        Set<String> roles = CollectionUtils.asSet(rolesArray);

        boolean flag=false;
        for(String role: roles){
            System.out.println("roles:" + role);
            if(subject.hasRole(role)){
                flag=true;
                break;
            }
        }
        return flag;
    }
}