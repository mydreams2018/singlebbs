package cn.kungreat.singlebbs.security;

import cn.kungreat.singlebbs.domain.User;
import cn.kungreat.singlebbs.service.PermissionService;
import cn.kungreat.singlebbs.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;


public class MyUserDetails implements UserDetailsService {
    private final UserService userService;
    private final PermissionService permissionService;
    public MyUserDetails(UserService userService, PermissionService permissionService) {
        this.userService = userService;
        this.permissionService = permissionService;
    }

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