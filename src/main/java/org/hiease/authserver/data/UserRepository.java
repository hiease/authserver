package org.hiease.authserver.data;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
	@Override
	User save(@Param("user") User user);

	List<User> findAll();

	@Query("from User u where u.username = ?#{principal.username}")
	User findCurrentUser();

	User findByUsername(@Param("username") String username);

	User findByUsernameAndStatus(@Param("username") String username, @Param("state") String state);

	Page<User> findByUsernameContainsOrNameContainsOrMobileContains(@Param("username") String username, @Param("name") String name, @Param("mobile") String mobile, Pageable pageable);

	@Modifying
	@Query("update User u set u.password = :passwd where u.username = ?#{principal.username}")
	@Transactional
	void updateUserPasswd(@Param("passwd") String passwd);

}
