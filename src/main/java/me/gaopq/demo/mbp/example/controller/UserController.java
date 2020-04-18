package me.gaopq.demo.mbp.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.gaopq.demo.mbp.example.dto.user.*;
import me.gaopq.demo.mbp.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author gaopq
 * @date 2020/4/17 18:31
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户管理")
public class UserController {

    @Resource
    private UserService userService;


    @PostMapping
    @ApiOperation(value = "添加用户", response = UserInfoDTO.class)
    public ResponseEntity addUser(@RequestBody @Validated AddUserDTO data) {

        return ResponseEntity.ok(userService.addUser(data));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "查询用户信息", response = UserInfoDTO.class)
    public ResponseEntity getUserBaseInfo(@PathVariable Integer id) {

        return ResponseEntity.ok(userService.getUserBaseInfo(id));
    }

    @PutMapping("{id}")
    @ApiOperation(value = "修改用户基本信息", response = UserInfoDTO.class)
    public ResponseEntity updateUserBaseInfo(@PathVariable Integer id,
                                             @RequestBody @Validated UpdateUserDTO data) {

        return ResponseEntity.ok(userService.updateUserBaseInfo(id, data));
    }

    @GetMapping("info")
    @ApiOperation(value = "[分页]分页查询用户简单信息 - 表内分页查询", response = UserInfoPageDTO.class, responseContainer = "List")
    public ResponseEntity pageUserInfo(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                       @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        return ResponseEntity.ok(userService.pageUserInfo(pageNum, pageSize));
    }

    @GetMapping("detail-info")
    @ApiOperation(value = "[分页]分页查询用户明细信息 - 跨表查询", response = UserDetailInfoPageDTO.class, responseContainer = "List")
    public ResponseEntity pageUserDetailInfo(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                             @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        return ResponseEntity.ok(userService.pageUserDetailInfo(pageNum, pageSize));
    }
}
