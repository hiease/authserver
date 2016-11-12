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
public class Resource extends AbstractEntity {

    private String moduleId;

    private String name;

    private String url;

    private String type;

    private Long parentId;

    private Integer showOrder;

    private String icon;

    @OneToMany(mappedBy="parentId")
    @OrderColumn(name = "showOrder")
    private List<Resource> children = new LinkedList<Resource>();
}