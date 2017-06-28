package org.hiease.authserver.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by qihaiyan on 2017/1/14.
 */

@Projection(name = "excerpt", types = Department.class)
public interface DepartmentProjection {
    Long getId();
    String getName();

    @Value("#{target.organization}")
    Branch getOrganization();
}
