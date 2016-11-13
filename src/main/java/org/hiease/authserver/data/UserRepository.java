package org.hiease.authserver.data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	@Override
	User save(@Param("user") User user);

	List<User> findAll();

	@Query("from User u where u.username = ?#{principal}")
	User findCurrentUser();

	User findByUsername(@Param("username") String username);

	User findByUsernameAndStatus(@Param("username") String username, @Param("state") String state);

	@Modifying
	@Query("update User u set u.password = :passwd where u.username = ?#{principal}")
	@Transactional
	void updateUserPasswd(@Param("passwd") String passwd);

}
