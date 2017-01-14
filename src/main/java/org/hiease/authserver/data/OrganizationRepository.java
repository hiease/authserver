package org.hiease.authserver.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by qihaiyan on 2017/1/14.
 */
public interface OrganizationRepository extends CrudRepository<Organization, Long> {

//    @Override
//    Organization save(@Param("organization") Organization organization);

//    @Override
//    List<Organization> findAll();

}