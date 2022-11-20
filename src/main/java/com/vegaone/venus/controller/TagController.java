package com.vegaone.venus.controller;

import com.vegaone.venus.dto.Tag;
import com.vegaone.venus.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("{id}")
    public Tag getTag(@PathVariable Long id) {
        return tagService.getTag(id);
    }

    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

    @PutMapping
    public Tag updateTag(@RequestBody Tag tag) {
        return tagService.updateTag(tag);
    }

    @DeleteMapping("{id}")
    public void deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
    }

    @GetMapping("/project/{projectId}")
    public List<Tag> getAllTagsByProjectId(@PathVariable Long projectId) {
        return tagService.getAllTagsByProjectId(projectId);
    }
}
