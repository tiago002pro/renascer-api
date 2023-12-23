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
        value = "SELECT * FROM video v WHERE v.category = :category")
    List<Video> findAllByCategory(@Param("category") String category);
}
