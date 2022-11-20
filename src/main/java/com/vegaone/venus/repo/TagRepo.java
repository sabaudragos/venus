package com.vegaone.venus.repo;

import com.vegaone.venus.domain.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepo extends JpaRepository<TagEntity, Long> {

    @Query("select te from TagEntity te where te.project.id = :projectId")
    List<TagEntity> findAllByProjectId(Long projectId);
}
