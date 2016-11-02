package org.hiease.authserver.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class User extends BaseEntity {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private String username;
    private String name;
    private String gender;
    private String status;
    private String email;
    private String mobile;
    private String department;
    private String isAdmin;

    @JsonIgnore
    private String password;

    @OneToMany(fetch= FetchType.LAZY, cascade= CascadeType.ALL, mappedBy="user")
    private List<UserRole> userRoles;

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }
}
