package cn.kungreat.singlebbs.security;

import cn.kungreat.singlebbs.SinglebbsApplication;
import cn.kungreat.singlebbs.util.Calculator;
import cn.kungreat.singlebbs.vo.JsonResult;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ImageFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest re = request;
        String requestURI = re.getRequestURI();
        if("/api/defaultLogin".equals(requestURI)){
            //如果已经存在认证对象 时不要再次认证
            SecurityContext context = SecurityContextHolder.getContext();
            if(context.getAuthentication() != null){
                String isAuth = context.getAuthentication().getName();
                if(isAuth != null && isAuth.length()!=0 && !"anonymousUser".equals(isAuth)){
                    re.getSession().removeAttribute("image_code");
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(SinglebbsApplication.MAP_JSON.writeValueAsString(new JsonResult(false,"已经存在用户,请先退出",null,0,"imgCode")));
                    return ;
                }
            }
        }
        if("/api/defaultLogin".equals(requestURI) || "/api/report/insert".equals(requestURI)
            || "/api/report/update".equals(requestURI)){
            Object code = re.getSession().getAttribute("image_code");
            Object obj = re.getSession().getAttribute("time");
            re.getSession().removeAttribute("image_code");
            long time = (obj==null?65000:System.currentTimeMillis()-(long)obj);
            Object tarCode = request.getParameter("image_code");
            if(code == null || obj ==null || time > 60000 || tarCode ==null || Calculator.count(code.toString()) != Integer.parseInt(tarCode.toString())){
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(SinglebbsApplication.MAP_JSON.writeValueAsString(new JsonResult(false,"验证码错误|或者超时,请注意计算优先级",null,0,"imgCode")));
                return;
            }
        }
        filterChain.doFilter(request,response);
    }
}