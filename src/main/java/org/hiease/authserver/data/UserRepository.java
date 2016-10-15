package org.hiease.authserver.data;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends Repository<User, String> {
	User findByUsername(@Param("username") String username);
	User findByUsernameAndStatus(@Param("username") String username, @Param("state") String state);
}
