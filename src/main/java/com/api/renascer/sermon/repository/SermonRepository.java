package com.api.renascer.sermon.repository;

import com.api.renascer.sermon.model.Sermon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SermonRepository extends JpaRepository<Sermon, Long> {
}
