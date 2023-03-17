package cn.kungreat.singlebbs.security;

import cn.kungreat.singlebbs.SinglebbsApplication;
import cn.kungreat.singlebbs.vo.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AuthManagerFilter extends OncePerRequestFilter {

    private static final List<String> manager = List.of("deathwater","kungreat");

    private static final List<String> rootManagerPath = List.of("/api/manager/getAllUser","/api/manager/updateUserIsManager"
                                ,"/api/manager/updatePortIsTop","/api/manager/updateUserState","/api/manager/collaborationInsert"
                                ,"/api/manager/uploadImg");
    /*
    * TokenManagerFilter 之后
    */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/api/manager")) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication instanceof AnonymousAuthenticationToken) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(SinglebbsApplication.MAP_JSON.writeValueAsString(new JsonResult(false, "不是管理员,没权审核", null, 0, "imgCode")));
                return;
            }
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            Byte isManager = loginUser.getUser().getIsManager();
            if (isManager != 1) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(SinglebbsApplication.MAP_JSON.writeValueAsString(new JsonResult(false, "不是管理员,没权审核", null, 0, "imgCode")));
                return;
            }
            if(rootManagerPath.contains(requestURI) && !manager.contains(authentication.getName())){
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(SinglebbsApplication.MAP_JSON.writeValueAsString(new JsonResult(false, "不是超级管理员,没权操作", null, 0, "imgCode")));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
