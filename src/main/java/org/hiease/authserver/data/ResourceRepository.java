package org.hiease.authserver.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    @Override
    Resource save(@Param("resource") Resource resource);

    @Query("select r from Resource r inner join r.roles role where r.parentId is null and role.id in (select ur.id from User u inner join u.roles ur where u.username = ?#{principal.username})")
    List<Resource> findByCurrentUser();

    @Query("select r from Resource r inner join r.roles role where role.id = ?1")
    List<Resource> findByRoleId(@Param("roleId") Long roleId);

    @Query("from Resource where parentId is null")
    List<Resource> findRootResource();

    @Query("from Resource where parentId=?1")
    List<Resource> findByParent(@Param("parentId") Long parentId);
}
