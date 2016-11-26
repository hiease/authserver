package org.hiease.authserver.data;

import lombok.*;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@ToString
public class Module extends AbstractEntity {

    private String name;

    private String fullName;

    private String url;

}