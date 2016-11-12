package org.hiease.authserver.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ResourceRepository extends PagingAndSortingRepository<Resource, Long> {

    @Override
    Resource save(@Param("resource") Resource resource);

    @Query("from Resource where id in (select resource.id from RoleResource where role in (select role from UserRole where user.username = ?#{principal}))")
    List<Resource> findByCurrentUser();

    @Query("from Resource where parentId is null)")
    List<Resource> findRootResource();

    @Query("from Resource where parentId=?1")
    List<Resource> findByParent(@Param("parentId") Long parentId);
}
