package com.vegaone.venus.repo;

import com.vegaone.venus.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllByCompanyId(Long id);

    List<UserEntity> findAllByProjectId(Long id);

    List<UserEntity> findAllByCompanyIdAndProjectId(Long companyId, Long projectId);
}
