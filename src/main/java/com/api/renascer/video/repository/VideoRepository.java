package com.api.renascer.video.repository;

import com.api.renascer.video.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    @Query(nativeQuery = true,
        value = "SELECT * FROM video v WHERE v.category LIKE concat('%', :category, '%') ORDER BY v.date DESC")
    Page<Video> findAllByCategory(@Param("category") String category, Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT * FROM video v ORDER BY v.date DESC")
    Page<Video> findLatest(Pageable pageable);

    @Query(nativeQuery = true,
            value = " SELECT * FROM video v " +
                    " WHERE lower(v.title) LIKE concat('%', lower(:search), '%')" +
                    "   OR lower(v.author) LIKE concat('%', lower(:search), '%') " +
                    " ORDER BY v.date DESC")
    List<Video> searchVideos(@Param("search") String search);

    @Query(nativeQuery = true,
            value = " SELECT * FROM video v WHERE v.notified IS FALSE ORDER BY v.id ")
    List<Video> findVideosToNotify();

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = " UPDATE video " +
                    " SET notified = TRUE " +
                    " WHERE id IN :ids ")
    void readVideosByIds(@Param("ids") List<Long> ids);

    @Query(nativeQuery = true, value = "SELECT * FROM video v ORDER BY v.date DESC LIMIT 1")
    Video findLastVideo();
}
