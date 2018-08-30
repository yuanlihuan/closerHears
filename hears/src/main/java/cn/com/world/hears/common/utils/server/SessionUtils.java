package cn.com.world.hears.common.utils.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionUtils {

	/**
	 * 获取request
	 */
	public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();  
        return requestAttributes == null?null:requestAttributes.getRequest();
	}
	
	/**
	 * 获取session
	 * @return
	 */
	public static HttpSession getSession() {
		return getRequest().getSession(false);
	}
	
	/**
	 * 获取真实的路径
	 * @return
	 */
	public static String getRealRootPath() {
		return getRequest().getServletContext().getRealPath("/");
	}
	
	/**
	 * 获取Ip
	 */
	public static String getIp() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder  
                .getRequestAttributes();
		if(servletRequestAttributes != null) {
			HttpServletRequest request = servletRequestAttributes.getRequest();
			return request.getRemoteAddr();
		}
		return null;
	}
	
	/**
     * 设置session的Attribute
     * @param name
     * @param value
     */
	public static void setSessionAttribute(String name, Object value) {
		HttpServletRequest request = getRequest();
		if (request != null) {
			request.getSession().setAttribute(name, value);
		}
	}
	
	/**
     * 获取session中的Attribute
     * @param name
     * @return
     */
	public static Object getSessionAttribute(String name) {
		HttpServletRequest request = getRequest();
		return request==null?null:request.getSession().getAttribute(name);
	}
	
	 /**
     * 设置request的Attribute
     * @param name
     * @param value
     */
    public static void setRequestAttribute(String name,Object value){  
        HttpServletRequest request = getRequest();  
        if(request!=null){  
            request.setAttribute(name, value);    
        }  
    } 
    
    /**
     * 获取上下文path
     * @return
     */
    public static String getContextPath() {  
        return getRequest().getContextPath();  
    } 
    
    /**
     * 删除session中的Attribute
     * @param name
     */
    public static void removeSessionAttribute(String name) {  
        getRequest().getSession().removeAttribute(name);  
    }  
	
	
}
