package cn.kungreat.singlebbs.config;

import cn.kungreat.singlebbs.filter.AnotherImageFilter;
import cn.kungreat.singlebbs.security.MyUserDetails;
import cn.kungreat.singlebbs.service.PermissionService;
import cn.kungreat.singlebbs.service.UserService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;

import javax.servlet.Filter;

@Configuration
public class BeanConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(UserService userService, PermissionService permissionService) {
        return new MyUserDetails(userService,permissionService);
    }
    @Bean
    public FilterRegistrationBean<Filter> EKPSSOClientAuthentication() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new AnotherImageFilter());
        registration.addUrlPatterns("/register","/user/resetPassword");
        registration.setName("anotherImageFilter");
        return registration;
    }

   /* @Bean   RequestContextListener 可以不用?
    public ServletListenerRegistrationBean<RequestContextListener> requestContextListener(){
        ServletListenerRegistrationBean<RequestContextListener> listener = new ServletListenerRegistrationBean<>();
        listener.setListener(new RequestContextListener());
        listener.setOrder(1);
        return listener;
    }*/

    @Bean
    public ClientRegistrationRepository MyClientRegistrationRepository(){
        return registrationId -> MyClientRegistrations.valueOf(registrationId.toUpperCase()).getClientRegistration();
    }

    @Bean
    public DefaultOAuth2AuthorizationRequestResolver defaultOAuth2AuthorizationRequestResolver(ClientRegistrationRepository clientRegistrationRepository){
        return new DefaultOAuth2AuthorizationRequestResolver(clientRegistrationRepository,"/authorization/login");
    }

}
