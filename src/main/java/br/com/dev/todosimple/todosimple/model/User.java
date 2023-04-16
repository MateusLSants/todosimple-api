package br.com.dev.todosimple.todosimple.model;

import br.com.dev.todosimple.todosimple.model.enums.ProfilesEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = User.TABLE_NAME)
@Data
public class User extends AbstractEntity {
    public static final String TABLE_NAME = "user";

    @Column(name = "name", length = 100, nullable = false, unique = false)
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String name;

    @Column(name = "password", length = 60, nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @NotEmpty
    @Size(min = 8, max = 60)
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Task> tasks = new ArrayList<Task>();

    @ElementCollection(fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CollectionTable(name = "user_profile")
    @Column(name = "profile", nullable = false)
    private Set<Integer> profiles = new HashSet<>();

    public Set<ProfilesEnum> getProfiles() {
        return this.profiles.stream().map(x -> ProfilesEnum.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(ProfilesEnum profilesEnum) {
        this.profiles.add(profilesEnum.getCode());
    }

}
