package org.jhay.common.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static String extractUserEmail(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
