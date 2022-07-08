package cn.kungreat.singlebbs.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class UserContext {
	private UserContext(){}
	
	public static HttpSession getSession(){
		RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes ServletRequestAttributes = (ServletRequestAttributes) attributes;
		return ServletRequestAttributes.getRequest().getSession();
	}
}