package br.com.dev.todosimple.todosimple.Security;

import br.com.dev.todosimple.todosimple.model.enums.ProfilesEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class UserSpringSecurity implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSpringSecurity(
            Long id,
            String username,
            String password,
            Set<ProfilesEnum> profilesEnums) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = profilesEnums.stream().map(x -> new SimpleGrantedAuthority(x.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(ProfilesEnum profilesEnum) {
        return getAuthorities().contains(new SimpleGrantedAuthority(profilesEnum.getDescription()));
    }
}
