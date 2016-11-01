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
public class Department extends BaseEntity{
    private String name;
    private String level;
    private String parentId;
    private String type;
    private String province;
    private String city;
    private String area;
    private String status;
}