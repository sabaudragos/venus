package com.vegaone.venus.controller;

import com.vegaone.venus.dto.Feature;
import com.vegaone.venus.service.FeatureService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feature")
public class FeatureController {

    private final FeatureService featureService;

    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

    @GetMapping("{id}")
    public Feature getFeature(@PathVariable Long id) {
        return featureService.getFeature(id);
    }

    @PostMapping
    public Feature createFeature(@RequestBody Feature feature) {
        return featureService.createFeature(feature);
    }

    @PutMapping
    public Feature updateFeature(@RequestBody Feature feature) {
        return featureService.updateFeature(feature);
    }

    @DeleteMapping("{id}")
    public void deleteFeature(@PathVariable Long id) {
        featureService.deleteFeature(id);
    }

    @GetMapping("/project/{projectId}")
    public List<Feature> getAllFeaturesByProjectId(@PathVariable Long projectId) {
        return featureService.getAllFeaturesByProjectId(projectId);
    }
}
