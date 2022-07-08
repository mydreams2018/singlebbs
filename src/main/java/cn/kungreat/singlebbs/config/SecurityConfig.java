package cn.kungreat.singlebbs.config;

import cn.kungreat.singlebbs.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.*;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.SessionManagementFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetails myUserDetails;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private SuccessHandler successHandler;
    @Autowired
    private FaliureHandler faliureHandler;
    @Autowired
    private PersistentTokenRepository tokenRepository;
    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    private DefaultOAuth2AuthorizationRequestResolver defaultOAuth2AuthorizationRequestResolver;
    @Autowired
    private MyOAuth2UserService myOAuth2UserService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetails).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 接口层要获取认证对象的时候  不要在这里放行 这里 不会封装认证对象过来
        web.ignoring().antMatchers(
                "/image","/register","/userImg/**"
                ,"/report/queryReport","/report/selectByPrimaryKey","/detailsText/queryDetails"
        ,"/user/home","/user/lastSendPort","/user/lastReplyPort","/userReplyPort/selectAll","/user/resetPassword"
        ,"/actuator/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // by default uses a Bean by the name of corsConfigurationSource
                // if Spring MVC is on classpath and no CorsConfigurationSource is provided,
                // Spring Security will use CORS configuration provided to Spring MVC
                .cors().and().csrf().disable()
                .oauth2Login(oauth2 -> oauth2
                        .clientRegistrationRepository(clientRegistrationRepository)
                        .loginPage("/index")
                        .authorizationEndpoint(authorization -> authorization
                                .baseUri("/authorization/login")
                                .authorizationRequestRepository(new HttpSessionOAuth2AuthorizationRequestRepository())
                                .authorizationRequestResolver(defaultOAuth2AuthorizationRequestResolver)
                        )
                        .redirectionEndpoint(redirection -> redirection
                                .baseUri("/auth/*")
                        )
                        .tokenEndpoint(token -> token
                                .accessTokenResponseClient(new MyOAuth2AccessTokenResponseClient())
                        )
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(myOAuth2UserService)
                        ).successHandler(new MyOauth2SuccessHandler()).failureHandler(new MyOauth2FailHandler())
                )
                .addFilterBefore(new ImageFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(new TokenManagerFilter(myUserDetails), SessionManagementFilter.class)
                .authorizeRequests()
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/index").loginProcessingUrl("/defaultLogin").permitAll()
                .successHandler(successHandler)
                .failureHandler(faliureHandler)
                .and()
                .logout().logoutUrl("/clearAll").clearAuthentication(true)
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutSuccessHandler(new LogoutHandler())
                .and()
                .rememberMe().tokenRepository(tokenRepository)
                .tokenValiditySeconds(60 * 10080)
                .userDetailsService(myUserDetails).authenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                String path = request.getRequestURI().substring(4);
                request.getRequestDispatcher(path).forward(request,response);
            }
        });

    }
}