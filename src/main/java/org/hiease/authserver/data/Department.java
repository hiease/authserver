package org.hiease.authserver.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Department extends AbstractEntity {
    private String name;
    private String level;
    private Long parentId;
    private String type;
    private String province;
    private String city;
    private String area;
    private String status;
}