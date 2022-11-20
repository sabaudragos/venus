package com.vegaone.venus.service;

import com.vegaone.venus.domain.TagEntity;
import com.vegaone.venus.dto.Tag;
import com.vegaone.venus.repo.TagRepo;
import com.vegaone.venus.util.MapperUtil;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TagService {

    private final TagRepo tagRepo;
    private final MapperUtil mapperUtil;

    public TagService(TagRepo tagRepo, MapperUtil mapperUtil) {
        this.tagRepo = tagRepo;
        this.mapperUtil = mapperUtil;
    }

    public Tag getTag(Long id) {
        TagEntity tagEntity = tagRepo
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No tag found with id=" + id));

        return mapperUtil.map(tagEntity, Tag.class);
    }

    public Tag createTag(Tag tag) {
        TagEntity savedTag = tagRepo.save(mapperUtil.map(tag, TagEntity.class));

        return mapperUtil.map(savedTag, Tag.class);
    }

    public Tag updateTag(Tag tag) {
        TagEntity savedTag = tagRepo.save(mapperUtil.map(tag, TagEntity.class));
        return mapperUtil.map(savedTag, Tag.class);
    }

    public void deleteTag(Long id) {
        if (tagRepo.existsById(id)) {
            tagRepo.deleteById(id);
        }
    }

    public List<Tag> getAllTagsByProjectId(Long projectId) {
        List<TagEntity> tagEntityList = tagRepo.findAllByProjectId(projectId);
        return mapperUtil.mapList(tagEntityList, Tag.class);
    }
}
