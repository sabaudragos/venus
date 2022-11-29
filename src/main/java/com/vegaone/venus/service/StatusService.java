package com.vegaone.venus.service;

import com.vegaone.venus.domain.StatusEntity;
import com.vegaone.venus.dto.Status;
import com.vegaone.venus.repo.StatusRepo;
import com.vegaone.venus.util.MapperUtil;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StatusService {

    private final StatusRepo statusRepo;
    private final MapperUtil mapperUtil;

    public StatusService(StatusRepo statusRepo, MapperUtil mapperUtil) {
        this.statusRepo = statusRepo;
        this.mapperUtil = mapperUtil;
    }

    public Status getStatus(Long id) {
        StatusEntity statusEntity = statusRepo
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No status found with id=" + id));

        return mapperUtil.map(statusEntity, Status.class);
    }

    public Status createStatus(Status status) {
        StatusEntity savedStatus = statusRepo.save(mapperUtil.map(status, StatusEntity.class));

        return mapperUtil.map(savedStatus, Status.class);
    }

    public Status updateStatus(Status status) {
        StatusEntity savedStatus = statusRepo.save(mapperUtil.map(status, StatusEntity.class));
        return mapperUtil.map(savedStatus, Status.class);
    }

    public void deleteStatus(Long id) {
        if (statusRepo.existsById(id)) {
            statusRepo.deleteById(id);
        }
    }

    public List<Status> getAllStatussByProjectId(Long projectId) {
        List<StatusEntity> statusEntityList = statusRepo.findAllByProjectId(projectId);
        return mapperUtil.mapList(statusEntityList, Status.class);
    }
}
