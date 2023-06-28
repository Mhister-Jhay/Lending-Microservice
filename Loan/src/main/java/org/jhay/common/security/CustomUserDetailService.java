package org.jhay.common.security;

import lombok.RequiredArgsConstructor;
import org.jhay.common.exception.UserNotFoundException;
import org.jhay.domain.model.User;
import org.jhay.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEmail(), new ArrayList<>());
    }
}
