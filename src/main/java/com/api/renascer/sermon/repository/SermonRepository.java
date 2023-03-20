package com.api.renascer.sermon.repository;

import com.api.renascer.sermon.model.Sermon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SermonRepository extends JpaRepository<Sermon, Long> {
    @Query(nativeQuery = true,
        value = "SELECT * FROM sermon s WHERE lower(s.speaker) LIKE concat('%', lower(:speaker), '%')")
    List<Sermon> findBySpeaker(@Param("speaker") String name);

    @Query(nativeQuery = true,
            value = "SELECT * FROM sermon s WHERE s.speaker IN (:speakers)")
    List<Sermon> findBySpeakers(@Param("speakers") List<String> speakers);

    @Query(nativeQuery = true,
            value = "SELECT s.speaker FROM sermon s WHERE lower(s.speaker) LIKE concat('%', lower(:name), '%') GROUP BY s.speaker ")
    List<String> searchAllSpeakers(@Param("name") String name);
}
