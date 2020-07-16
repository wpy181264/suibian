package com.dj.ssm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.pojo.ResultModel;
import com.dj.ssm.pojo.User;

import com.dj.ssm.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/users/")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResultModel get(@PathVariable Integer userId) {
        try {
            User user = userService.getById(userId);
            return new ResultModel().success(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }

    @PostMapping
    public ResultModel save(User user) {
        try {
            return new ResultModel().success(userService.save(user));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }

    @PutMapping
    public ResultModel update(User user) {
        try {
            return new ResultModel().success(userService.updateById(user));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResultModel delete(@PathVariable Integer userId) {
        try {
            return new ResultModel().success(userService.removeById(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }

    @GetMapping
    public ResultModel list1(Integer pageNo, Integer pageSize) {
        try {
            IPage pageInfo = new Page(pageNo, pageSize);
            IPage<User> page = userService.page(pageInfo);
            return new ResultModel().success(page);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }


    @PostMapping("/list")
    public ResultModel list2(@RequestBody User user) {
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("username", user.getUserName());
            List<User> userList = userService.list(queryWrapper);
            return new ResultModel().success(userList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }
}
