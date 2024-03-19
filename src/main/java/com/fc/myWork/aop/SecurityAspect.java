package com.fc.myWork.aop;

import com.fc.myWork.Service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.fc.myWork.Exception.AccessDeniedException;


@Aspect
@Component
public class SecurityAspect {

    @Autowired
    private UserService userService;

    /**
     * 该切面拦截所有使用了@PreAuthorize注解的方法，并且方法参数列表中包含userId参数
     * @param joinPoint
     * @param preAuthorize
     * @param userId
     */
    @Before("@annotation(preAuthorize) && args(userId, ..)")
    public void checkPermission(JoinPoint joinPoint, PreAuthorize preAuthorize, Long userId) {
        // 从注解中提取需要的权限
        String requiredAuthority = extractAuthority(preAuthorize);

        // 获取当前用户的权限信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = userService.loadUserByUsername(authentication.getName());

        // 校验用户是否拥有所需权限
        boolean hasPermission = userDetails.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(requiredAuthority));

        if (!hasPermission) {
            throw new AccessDeniedException("用户没有权限执行该操作");
        }
    }

    private String extractAuthority(PreAuthorize preAuthorize) {
        // 解析注解中的value值来获取所需权限
        // 假设value的格式为"hasAuthority('PERMISSION_NAME')"
        String value = preAuthorize.value();
        String requiredAuthority = value.substring(value.indexOf("'") + 1, value.lastIndexOf("'"));

        return requiredAuthority;
    }
}