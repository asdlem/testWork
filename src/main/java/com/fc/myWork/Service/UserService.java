package com.fc.myWork.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


public interface UserService {
    UserDetails loadUserByUsername(String name);
}
