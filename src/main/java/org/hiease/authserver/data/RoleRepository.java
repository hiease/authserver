package org.hiease.authserver.data;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, Long> {
//    List<Role> findByAppId(@Param("appId") String appId);
//    @Query("from Role where appId = :appId and roleId in (select roleId from UserRole where appId = :appId and userId = :userId)")
//    List<Role> findByAppIdAndUserId(@Param("appId") String appId, @Param("userId") String userId);
}
