package uz.fido.appnewssite.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.fido.appnewssite.entity.User;
import uz.fido.appnewssite.exceptions.ForbiddenException;

@Component
@Aspect
public class CheckPermissionExecutor {
    @Before(value = "@annotation(checkPermission)")
    public void checkPermissionMyMethod(CheckPermission checkPermission) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (user.getAuthorities().stream().filter())
        boolean exist = false;
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(checkPermission.role())){
                exist = true;
                break;
            }
        }

        if (!exist){
            throw new ForbiddenException(checkPermission.role(), "Ruxsat yoq");
        }
        //checkPermission.role();
    }
}
