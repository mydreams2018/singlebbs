package cn.kungreat.singlebbs.security;

import cn.kungreat.singlebbs.SinglebbsApplication;
import cn.kungreat.singlebbs.secure.CipherUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class TokenManagerFilter extends OncePerRequestFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(TokenManagerFilter.class);
    private final UserDetailsService myUserDetails;

    public TokenManagerFilter(UserDetailsService myUserDetails) {
        this.myUserDetails = myUserDetails;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader("jwtToken");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(jwtToken !=null &&!jwtToken.isBlank() && authentication instanceof AnonymousAuthenticationToken){
            try {
                String deCode = CipherUtils.DEFAULTA.decryptByIV(jwtToken,"AES/CBC/PKCS5Padding");
                Map<String,String> mapTokens = SinglebbsApplication.MAP_JSON.readValue(deCode, Map.class);
                UserDetails user = myUserDetails.loadUserByUsername(mapTokens.get("username"));
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken =
                        new PreAuthenticatedAuthenticationToken(user,authentication.getCredentials(),
                                user.getAuthorities());
                preAuthenticatedAuthenticationToken.setDetails(authentication.getDetails());
                context.setAuthentication(preAuthenticatedAuthenticationToken);
                SecurityContextHolder.setContext(context);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        filterChain.doFilter(request,response);
    }
}
