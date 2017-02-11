package org.hiease.authserver.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by qihaiyan on 2017/1/14.
 */

@Projection(name = "excerpt", types = User.class)
public interface UserProjection {
     String getUsername();
     String getName();
     String getGender();
     String getStatus();
     String getEmail();
     String getMobile();
     String getIsAdmin();
     String getAvatar();

    @Value("#{target.department}")
    Department getDepartment();

    @Value("#{target.department.organization}")
    Organization getOrganization();
}
