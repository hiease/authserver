package org.hiease.authserver.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class RoleResource extends BaseEntity {

    private String moduleId;
//    private String roleId;
    private String resourceId;
}