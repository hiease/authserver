package org.hiease.authserver.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {
    @Override
    Department save(@Param("department") Department department);
}
