package org.hiease.authserver.data;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
	@Override
	User save(@Param("user") User user);
	User findByUsername(@Param("username") String username);
	User findByUsernameAndStatus(@Param("username") String username, @Param("state") String state);

//	@Query("select m.userRoles. from User m where m.user.username = ?#{principal}")
//	List<UserRole> findAllResourcesByCurrentUser();
}
