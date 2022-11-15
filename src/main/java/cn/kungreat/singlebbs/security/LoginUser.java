package cn.kungreat.singlebbs.security;

import cn.kungreat.singlebbs.domain.User;
import cn.kungreat.singlebbs.util.UserAccumulate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LoginUser implements UserDetails, OAuth2User {
    private final Collection<? extends GrantedAuthority> grantedAuthorities;
    private final User user;
    private final Map<String, Object> oauth2User = new HashMap<>();
    public LoginUser(User user,Collection<? extends GrantedAuthority> grantedAuthorities){
        this.user = user;
        this.grantedAuthorities = grantedAuthorities;
        UserAccumulate.beanTransforMap(user,this.oauth2User);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getAccount();
    }

    //下面4个返回 false 会抛异常 .可以用来作状态处理
    @Override
    public boolean isAccountNonExpired() {
        return user.getState()==1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getAlias(){
        return user.getAlias();
    }

    @Override
    public String getName() {
        return this.user.getAccount();
    }

    public User getUser(){
        return this.user;
    }
}
