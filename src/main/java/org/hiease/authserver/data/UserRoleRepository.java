package org.hiease.authserver.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
    @Query("select m from UserRole m where m.user.username = ?#{principal}")
    List<UserRole> findByCurrentUser();
}
