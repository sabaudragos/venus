package com.vegaone.venus.controller;

import com.vegaone.venus.dto.User;
import com.vegaone.venus.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/company/{companyId}")
    public List<User> getAllUsersByCompanyId(@PathVariable Long companyId) {
        return userService.findAllByCompanyId(companyId);
    }

    @GetMapping("/project/{projectId}")
    public List<User> getAllUserByProjectId(@PathVariable Long projectId) {
        return userService.findAllByProjectId(projectId);
    }

    @GetMapping("/company/{companyId}/project/{projectId}")
    public List<User> getAllUsersByCompanyIdAndProjectId(@PathVariable Long companyId, @PathVariable Long projectId) {
        return userService.findAllByCompanyIdAndProjectId(companyId, projectId);
    }
}
