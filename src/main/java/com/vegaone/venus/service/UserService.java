package com.vegaone.venus.service;

import com.vegaone.venus.domain.UserEntity;
import com.vegaone.venus.dto.User;
import com.vegaone.venus.repo.UserRepo;
import com.vegaone.venus.util.MapperUtil;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final MapperUtil mapperUtil;

    public UserService(UserRepo userRepo, MapperUtil mapperUtil) {
        this.userRepo = userRepo;
        this.mapperUtil = mapperUtil;
    }

    public User getUser(Long id) {
        UserEntity userEntity = userRepo.findById(id).
                orElseThrow(() -> new EntityNotFoundException("No user found with id=" + id));
        return mapperUtil.map(userEntity, User.class);
    }

    public User createUser(User user) {
        UserEntity savedUser = userRepo.save(mapperUtil.map(user, UserEntity.class));

        return mapperUtil.map(savedUser, User.class);
    }

    public User updateUser(User user) {
        UserEntity userEntity = userRepo.save(mapperUtil.map(user, UserEntity.class));
        return mapperUtil.map(userEntity, User.class);
    }

    public void deleteUser(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        }
    }

    public List<User> findAllByCompanyId(Long companyId) {
        List<UserEntity> userEntityList = userRepo.findAllByCompanyId(companyId);
        return mapperUtil.mapList(userEntityList, User.class);
    }

    public List<User> findAllByProjectId(Long projectId) {
        List<UserEntity> userEntityList = userRepo.findAllByProjectId(projectId);
        return mapperUtil.mapList(userEntityList, User.class);
    }

    public List<User> findAllByCompanyIdAndProjectId(Long companyId, Long projectId) {
        List<UserEntity> userEntityList = userRepo.findAllByCompanyIdAndProjectId(companyId, projectId);
        return mapperUtil.mapList(userEntityList, User.class);
    }
}
