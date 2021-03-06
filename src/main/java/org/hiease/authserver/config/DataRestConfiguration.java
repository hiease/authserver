package org.hiease.authserver.config;

import org.hiease.authserver.data.Department;
import org.hiease.authserver.data.Branch;
import org.hiease.authserver.data.Resource;
import org.hiease.authserver.data.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class DataRestConfiguration {
    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return new RepositoryRestConfigurerAdapter() {
            @Override
            public void configureRepositoryRestConfiguration(
                    RepositoryRestConfiguration config) {
                config.exposeIdsFor(Resource.class);
                config.exposeIdsFor(Department.class);
                config.exposeIdsFor(Role.class);
                config.exposeIdsFor(Branch.class);
            }
        };
    }
}