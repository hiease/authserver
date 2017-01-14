package org.hiease.authserver.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Department extends AbstractEntity {
    private String name;
    private Long level;
    private Long parentId;
//    private Long orgId;
    private String type;
    private String province;
    private String city;
    private String area;
    private String status;

    @ManyToOne
    @JoinColumn(name="org_id")
    private Organization organization;
}