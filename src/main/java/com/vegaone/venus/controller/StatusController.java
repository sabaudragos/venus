package com.vegaone.venus.controller;

import com.vegaone.venus.dto.Status;
import com.vegaone.venus.service.StatusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("{id}")
    public Status getStatus(@PathVariable Long id) {
        return statusService.getStatus(id);
    }

    @PostMapping
    public Status createStatus(@RequestBody Status status) {
        return statusService.createStatus(status);
    }

    @PutMapping
    public Status updateStatus(@RequestBody Status status) {
        return statusService.updateStatus(status);
    }

    @DeleteMapping("{id}")
    public void deleteStatus(@PathVariable Long id) {
        statusService.deleteStatus(id);
    }

    @GetMapping("/project/{projectId}")
    public List<Status> getAllStatussByProjectId(@PathVariable Long projectId) {
        return statusService.getAllStatussByProjectId(projectId);
    }
}
