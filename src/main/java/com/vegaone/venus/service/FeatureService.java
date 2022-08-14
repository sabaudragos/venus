package com.vegaone.venus.service;

import com.vegaone.venus.domain.FeatureEntity;
import com.vegaone.venus.dto.Feature;
import com.vegaone.venus.repo.FeatureRepo;
import com.vegaone.venus.util.MapperUtil;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class FeatureService {

    private final FeatureRepo featureRepo;
    private final MapperUtil mapperUtil;

    public FeatureService(FeatureRepo featureRepo, MapperUtil mapperUtil) {
        this.featureRepo = featureRepo;
        this.mapperUtil = mapperUtil;
    }

    public Feature getFeature(Long id) {
        FeatureEntity featureEntity = featureRepo
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No feature found with id=" + id));

        return mapperUtil.map(featureEntity, Feature.class);
    }

    public Feature createFeature(Feature feature) {
        FeatureEntity savedFeature = featureRepo.save(mapperUtil.map(feature, FeatureEntity.class));

        return mapperUtil.map(savedFeature, Feature.class);
    }

    public Feature updateFeature(Feature feature) {
        FeatureEntity savedFeature = featureRepo.save(mapperUtil.map(feature, FeatureEntity.class));
        return mapperUtil.map(savedFeature, Feature.class);
    }

    public void deleteFeature(Long id) {
        if (featureRepo.existsById(id)) {
            featureRepo.deleteById(id);
        }
    }

    public List<Feature> getAllFeaturesByProjectId(Long projectId) {
        List<FeatureEntity> featureEntityList = featureRepo.findAllByProjectId(projectId);
        return mapperUtil.mapList(featureEntityList, Feature.class);
    }
}
