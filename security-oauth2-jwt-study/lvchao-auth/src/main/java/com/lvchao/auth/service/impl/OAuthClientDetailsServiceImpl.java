package com.lvchao.auth.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/21
 */
@Service
public class OAuthClientDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = User.withUsername("lvchao").password(new BCryptPasswordEncoder().encode("123")).authorities("p1").build();
        return userDetails;
    }
}
