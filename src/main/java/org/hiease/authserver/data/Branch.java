package org.hiease.authserver.data;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by qihaiyan on 2017/1/14.
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "children")
@Entity
@NoArgsConstructor
public class Branch extends AbstractEntity {
    private String code;
    private String name;
    private Long level;
    private Long parentId;
    private String province;
    private String city;
    private String area;
    private String status;

//    @OneToMany(mappedBy="organization")
//    private List<Department> departments;

    @OneToMany(mappedBy="parentId")
    private List<Branch> children = new LinkedList<Branch>();
}
