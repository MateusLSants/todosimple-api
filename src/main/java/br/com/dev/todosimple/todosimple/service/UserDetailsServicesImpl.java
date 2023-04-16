package br.com.dev.todosimple.todosimple.service;

import br.com.dev.todosimple.todosimple.Security.UserSpringSecurity;
import br.com.dev.todosimple.todosimple.model.User;
import br.com.dev.todosimple.todosimple.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServicesImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByName(username);
        if(Objects.isNull(user))
            throw new UsernameNotFoundException("User not found: " + username);
        return new UserSpringSecurity(user.getId(), user.getName(), user.getPassword(), user.getProfiles());
    }
}
