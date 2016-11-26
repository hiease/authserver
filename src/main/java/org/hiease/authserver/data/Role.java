package org.hiease.authserver.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "resources")
@Entity
@NoArgsConstructor
public class Role extends AbstractEntity {
    private String moduleId;
    private String authority;
    private String name;
    private String status = "0";
    private String permission;

    @ManyToMany(mappedBy="roles")
    private List<User> users;

    @ManyToMany
    @JoinTable(name="role_resource",joinColumns=@JoinColumn(name="role_id"),
            inverseJoinColumns=@JoinColumn(name="resource_id"))
    private List<Resource> resources;
}