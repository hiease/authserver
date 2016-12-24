package org.hiease.authserver.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by qihaiyan on 2016/12/24.
 */

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Override
    Notification save(@Param("notification") Notification notification);

    @Query("select r from Notification r where r.username = ?#{principal.username}) or r.username is null")
    List<Resource> findByCurrentUser();
}
