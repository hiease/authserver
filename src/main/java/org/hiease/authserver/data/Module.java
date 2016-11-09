package org.hiease.authserver.data;

import lombok.*;

import javax.persistence.Entity;

@Data
@Entity
//@NoArgsConstructor
public class Module extends AbstractEntity {

    private String name;

    private String fullName;

    private String url;

}