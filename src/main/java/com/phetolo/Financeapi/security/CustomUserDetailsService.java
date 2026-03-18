package com.phetolo.Financeapi.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.phetolo.Financeapi.model.User;
import com.phetolo.Financeapi.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo;
    private User user;
    public CustomUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        user = repo.findByEmail(email).get();

        String role = (user.getRole() != null) ? user.getRole().name() : "USER"; // default
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_" + role))
        );
    }
    
    public Collection<? extends GrantedAuthority> getAuthorities(){
    	return List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole().name()));
    }
}