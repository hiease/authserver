package org.hiease.authserver.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Role extends BaseEntity {
    private String moduleId;
    private String name;
    private String status;
    private String permission;

    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="role")
    private List<RoleResource> roleResources;
}