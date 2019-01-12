package com.uca.tfg.security;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uca.tfg.repository.UserRepository;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository users;

    public UserDetailsServiceImpl(UserRepository applicationUserRepository) {
        this.users = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        com.uca.tfg.model.User applicationUser = users.findByEmail(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getEmail(), applicationUser.getPassword(), emptyList());
    }
}