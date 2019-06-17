package com.nixsolutions.ppp.service;

import com.nixsolutions.ppp.model.dao.UserDao;
import com.nixsolutions.ppp.model.entity.Role;
import com.nixsolutions.ppp.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.getByLogin(email);
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(Role.getUserRole().getName()));

        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getLogin())
                .password(encoder.encode(user.getPassword()))
                .roles(user.getRole().getName()).build();
        return userDetails;
    }
}