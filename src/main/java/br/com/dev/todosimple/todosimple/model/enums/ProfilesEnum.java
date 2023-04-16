package br.com.dev.todosimple.todosimple.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum ProfilesEnum {
    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private Integer code;
    private String description;

    public static ProfilesEnum toEnum(Integer code) {
        if(Objects.isNull(code))
            return null;
        for (ProfilesEnum x: ProfilesEnum.values()) {
            if(code.equals(x.getCode()))
                return x;
        }

        throw new IllegalArgumentException("Invalid code" + code);
    }

}
