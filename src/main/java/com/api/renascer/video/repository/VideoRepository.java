package com.api.renascer.video.repository;

import com.api.renascer.video.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    @Query(nativeQuery = true,
        value = "SELECT * FROM video v WHERE v.category LIKE concat('%', :category, '%') ORDER BY v.date DESC")
    List<Video> findAllByCategory(@Param("category") String category);

    @Query(nativeQuery = true,
            value = "SELECT * FROM video v ORDER BY v.date DESC LIMIT 10")
    List<Video> findLatest();

    @Query(nativeQuery = true,
            value = " SELECT * FROM video v " +
                    " WHERE lower(v.title) LIKE concat('%', lower(:search), '%')" +
                    "   OR lower(v.author) LIKE concat('%', lower(:search), '%') " +
                    " ORDER BY v.date")
    List<Video> searchVideos(@Param("search") String search);

    @Query(nativeQuery = true,
            value = " SELECT * FROM video v " +
                    " WHERE NOT EXISTS (" +
                    "   SELECT 1 FROM notification n" +
                    "   WHERE n.entity_id = v.id AND n.type = 'VIDEOS'" +
                    " ) " +
                    " ORDER BY v.id ")
    List<Video> findVideosToNotify();
}
