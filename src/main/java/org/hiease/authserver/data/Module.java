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
public class Module extends BaseEntity{

    private String name;

    private String fullName;

    private String url;

}