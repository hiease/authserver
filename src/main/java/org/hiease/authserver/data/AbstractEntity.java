package org.hiease.authserver.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue Long id;

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

    @ManyToOne
    @JoinColumn(name="tenant_id")
    private Tenant tenant;

}
