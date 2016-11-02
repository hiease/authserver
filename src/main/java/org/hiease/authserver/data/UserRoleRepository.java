package org.hiease.authserver.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, String> {
    @Query("select m from UserRole m where m.user.username = ?#{principal}")
    List<UserRole> findByCurrentUser();
}
