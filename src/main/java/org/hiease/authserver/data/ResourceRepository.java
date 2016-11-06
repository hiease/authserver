package org.hiease.authserver.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface ResourceRepository extends PagingAndSortingRepository<Resource, UUID> {

//    List<Resource> findByAppId(@Param("appId") String appId);
//    List<Resource> findByAppIdOrderByShowOrder(@Param("appId") String appId);
//    List<Resource> findByAppIdAndMenuLevel(@Param("appId") String appId, @Param("menuLevel") String menuLevel);
//    List<Resource> findByAppIdAndMenuLevelAndParentMenu(@Param("appId") String appId, @Param("menuLevel") String menuLevel, @Param("parentMenu") String parentMenu);
//    Resource findByAppIdAndMenuId(@Param("appId") String appId, @Param("menuId") String menuId);
    @Query("from Resource where id in (select resource.id from RoleResource where role in (select role from UserRole where user.username = ?#{principal}))")
    List<Resource> findByCurrentUser();
//    @Query("from Resource where appId = :appId and menuId in (select menuId from RoleResource where roleId in (select roleId from UserRole where userId = :userId)) order by appId, menuLevel, parentMenu, showOrder")
//    List<Resource> findByAppIdAndUserId(@Param("appId") String appId, @Param("userId") String userId);
//    @Query("from Resource where appId = :appId and menuId in (select menuId from RoleResource where roleId in (:roleIds)) order by appId, menuLevel, parentMenu, showOrder")
//    List<Resource> userAuthMenus(@Param("appId") String appId, @Param("roleIds") String[] roleIds);
}
