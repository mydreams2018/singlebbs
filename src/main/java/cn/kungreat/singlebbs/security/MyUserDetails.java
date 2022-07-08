package cn.kungreat.singlebbs.security;

import cn.kungreat.singlebbs.domain.User;
import cn.kungreat.singlebbs.service.PermissionService;
import cn.kungreat.singlebbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyUserDetails implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectByPrimaryKey(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        Set<GrantedAuthority> roles = new LinkedHashSet<>();
        List<String> ps = permissionService.selectPermissions(username);
        if(ps != null && ps.size()>0){
            for(int x=0;x< ps.size();x++){
                roles.add(new SimpleGrantedAuthority("ROLE_"+ps.get(x)));
            }
        }
        return new LoginUser(user,roles);
    }
}