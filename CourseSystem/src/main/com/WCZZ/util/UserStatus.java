package main.com.WCZZ.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserStatus {
    public static boolean isAuthenticated(String sessionID, HttpServletRequest request, HttpServletResponse response){
            boolean status = false;

        SessionKey key = new WebSessionKey(sessionID,request,response);
        try{
            Session se = SecurityUtils.getSecurityManager().getSession(key);
            Object obj = se.getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY);
            if(obj != null){
                status = (Boolean) obj;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            Session se = null;
            Object obj = null;
        }

        return status;
    }

//    public UserInfo getUserInfo(String sessionID,HttpServletRequest request,HttpServletResponse response){
//        boolean status = false;
//        SessionKey key = new WebSessionKey(sessionID,request,response);
//        try{
//            Session se = SecurityUtils.getSecurityManager().getSession(key);
//            Object obj = se.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
//            //org.apache.shiro.subject.SimplePrincipalCollection cannot be cast to com.hncxhd.bywl.entity.manual.UserInfo
//            SimplePrincipalCollection coll = (SimplePrincipalCollection) obj;
//            return (UserInfo)coll.getPrimaryPrincipal();
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//        }
//        return null;
//    }
}
