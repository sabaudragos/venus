package com.vegaone.venus.repo;

import com.vegaone.venus.domain.FeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepo extends JpaRepository<FeatureEntity, Long> {

    @Query("select fe from FeatureEntity fe where fe.project.id = :projectId")
    List<FeatureEntity> findAllByProjectId(Long projectId);
}
