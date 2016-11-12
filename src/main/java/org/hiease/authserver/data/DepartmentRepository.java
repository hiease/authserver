package org.hiease.authserver.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {
}
