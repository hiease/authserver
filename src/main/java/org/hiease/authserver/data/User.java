package org.hiease.authserver.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Data
@ToString(exclude = {"password", "department", "roles"})
@EqualsAndHashCode(callSuper = true)
@Entity
public class User extends AbstractEntity {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private String username;
    private String name;
    private String gender;
    private String status;
    private String email;
    private String mobile;
    private String isAdmin;
    private String avatar;

    @JsonIgnore
    private String password;

    @ManyToOne
    @JoinColumn(name="department")
    private Department department;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }
}
