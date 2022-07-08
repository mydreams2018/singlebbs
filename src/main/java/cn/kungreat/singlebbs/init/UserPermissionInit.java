package cn.kungreat.singlebbs.init;

import cn.kungreat.singlebbs.domain.Permission;
import cn.kungreat.singlebbs.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class UserPermissionInit implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if(event.getApplicationContext() != null){
            Map<String,Object> beans = event.getApplicationContext().getBeansWithAnnotation(Controller.class);
            List<Permission> permissions = new ArrayList<>();
            for(Object bean:beans.values()){
                Method[] methods = bean.getClass().getMethods();
                for(Method mt : methods) {
                    PreAuthorize annotation = AnnotationUtils.findAnnotation(mt, PreAuthorize.class);
                    if(annotation != null) {
                        String cs[] = bean.getClass().getName().split("\\$");
                        String methodName = cs[0] +"-"+mt.getName();
                        Permission ps = new Permission();
                        ps.setPermissionMethods(methodName);
                        ps.setDescript(annotation.value().substring(9, annotation.value().length()-2));
                        permissions.add(ps);
                    }
                }
            }
            if(permissions != null && permissions.size() > 0){
                permissionMapper.insertBatch(permissions);
            }
        }
    }
}