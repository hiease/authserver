package org.hiease.authserver.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(excerptProjection = DepartmentProjection.class)
public interface DepartmentRepository extends CrudRepository<Department, Long> {
//    @Override
//    Department save(@Param("department") Department department);

//    @Override
//    List<Department> findAll();
}
