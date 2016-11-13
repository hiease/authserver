package org.hiease.authserver.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Override
    Role save(@Param("role") Role role);

//    @Modifying
//    @Transactional
//    @Query("delete from RoleResource u where u.role.id = ?1")
//    void removeAllRole();
}
