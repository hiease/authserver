package org.hiease.authserver.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnnouncementRepository extends PagingAndSortingRepository<Announcement, Long> {
    @Override
    Announcement save(@Param("announcement") Announcement announcement);

    List<Announcement> findByStatus(@Param("status") String status);
}
