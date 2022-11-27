package cn.kungreat.singlebbs.security;

import cn.kungreat.singlebbs.SinglebbsApplication;
import cn.kungreat.singlebbs.util.InvalidUserCacle;
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

public class InvalidUserFilter extends OncePerRequestFilter {
    /*
     * AuthManagerFilter 之后
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if (InvalidUserCacle.userIsInvalid(authentication.getName())) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(SinglebbsApplication.MAP_JSON.writeValueAsString(new JsonResult(false, "用户已失效", null, 0, "imgCode")));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
