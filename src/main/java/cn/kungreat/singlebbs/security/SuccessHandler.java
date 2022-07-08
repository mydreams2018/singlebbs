package cn.kungreat.singlebbs.security;

import cn.kungreat.singlebbs.SinglebbsApplication;
import cn.kungreat.singlebbs.util.JwtToken;
import cn.kungreat.singlebbs.vo.JsonResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler{

    //重定向工具类
//    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    //缓存上一次的重定向地址
//    private RequestCache requestCache = new HttpSessionRequestCache();

    /*
     *  spring 默认的处理器  SavedRequestAwareAuthenticationSuccessHandler 可以继承 默认的处理器
     *   在做一些判断 后调用 super(request,response,exception)返回默认流程处理
     *           也可自定意返回
     **/
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        JsonResult jsonResult = new JsonResult(true, authentication.getName(), "/index.html", 1, "");
        String jwtToken = JwtToken.getJwtToken(loginUser);
        if(jwtToken.isEmpty()){
            SecurityContextHolder.clearContext();
            jsonResult.setStatus(0);
            jsonResult.setResult(false);
            jsonResult.setMsg("生成token失败");
        }
        jsonResult.setRememberMe(request.getParameter("remember-me"));
        jsonResult.setJwtToken(jwtToken);
        response.getWriter().write(SinglebbsApplication.MAP_JSON.writeValueAsString(jsonResult));

   /*     SavedRequest cache = requestCache.getRequest(request, response);
        String path = (cache==null?"/index.html":cache.getRedirectUrl());
        String accept = request.getHeader("Accept");
        if(accept.contains("text/html")){
            response.sendRedirect(path);
        }else{
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(new JsonResult(true,authentication.getName(),path,1,"")));
        }*/
    }
}