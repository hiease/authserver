package org.hiease.authserver.data;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by qihaiyan on 2017/6/25.
 */

@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
@Data
@ToString(exclude = {"password", "department", "roles"})
@Entity
public class Tenant {
    @Id
    @GeneratedValue
    Long id;

    @CreatedBy
    String createdBy;

    @LastModifiedBy
    String lastModifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    Date lastModifiedDate;

    private String code;
    private String name;
}
