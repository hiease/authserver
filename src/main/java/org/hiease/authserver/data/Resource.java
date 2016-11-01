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
public class Resource extends BaseEntity {

    private String moduleId;

    private String name;

    private String url;

    private String type;

    private String status;

    private String parentId;

    private Integer order;

    private String icon;
}