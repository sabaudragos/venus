package com.vegaone.venus.repo;

import com.vegaone.venus.domain.StatusEntity;
import com.vegaone.venus.domain.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepo extends JpaRepository<StatusEntity, Long> {

    @Query("select se from StatusEntity se where se.project.id = :projectId")
    List<StatusEntity> findAllByProjectId(Long projectId);
}
