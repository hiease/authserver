package org.hiease.authserver.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString(exclude = {"organization"})
@Entity
@NoArgsConstructor
public class Department extends AbstractEntity {
    private String name;
    private Long level;
    private Long parentId;
    private String type;
    private String province;
    private String city;
    private String area;
    private String status;

    @ManyToOne
    @JoinColumn(name="branch_id")
    private Branch branch;
}