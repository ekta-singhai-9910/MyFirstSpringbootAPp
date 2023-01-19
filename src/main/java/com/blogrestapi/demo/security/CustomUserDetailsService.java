package com.blogrestapi.demo.security;

import com.blogrestapi.demo.model.User;
import com.blogrestapi.demo.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDataRepository userDataRepository ;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userDataRepository.findByEmailOrUsername(usernameOrEmail, usernameOrEmail)
                .orElseThrow(()->
                        new UsernameNotFoundException("Username not found for username or email: " + usernameOrEmail)) ;

        Set<GrantedAuthority> authorities = user
                .getRoles()
                .stream()
                .map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());


        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
