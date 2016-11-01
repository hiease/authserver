package org.hiease.authserver.data;

import org.springframework.data.repository.CrudRepository;

public interface RoleResourceRepository extends CrudRepository<RoleResource, String> {
//    List<RoleResource> findByAppIdAndRoleId(@Param("appId") String appId, @Param("roleId") String roleId);
//    @Query("from MRoleMenu where roleId in (select roleId from MUserRole where userId = (select userId from MUser where userAlias = :userAlias)) order by appId, roleId")
//    List<RoleResource> findByUserAlias(@Param("userAlias") String userAlias);
//    @Query("from MRoleMenu where appId = :appId and roleId in (select roleId from MUserRole where userId = :userId) order by appId, roleId")
//    List<RoleResource> findByAppIdAndUserId(@Param("appId") String appId, @Param("userId") String userId);
//    @Query("from MRoleMenu where appId = :appId and roleId in (:roleIds) order by appId, roleId")
//    List<RoleResource> userAuthRoleMenus(@Param("appId") String appId, @Param("roleIds") String[] roleIds);
//    @Modifying
//    @Query("delete from MRoleMenu where appId = :appId and roleId = :roleId")
//    @Transactional
//    void removeMenuByAppIdAndRoleId(@Param("appId") String appId, @Param("roleId") String roleId);
}
