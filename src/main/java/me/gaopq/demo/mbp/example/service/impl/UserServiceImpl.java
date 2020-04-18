package me.gaopq.demo.mbp.example.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import me.gaopq.demo.mbp.example.dto.user.*;
import me.gaopq.demo.mbp.example.entity.User;
import me.gaopq.demo.mbp.example.entity.UserExtension;
import me.gaopq.demo.mbp.example.mapper.UserMapper;
import me.gaopq.demo.mbp.example.service.UserExtensionService;
import me.gaopq.demo.mbp.example.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author gaopq
 * @date 2020/4/17 18:30
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserExtensionService extensionService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfoDTO addUser(AddUserDTO data) {
        User user;
        String phone = data.getPhone();
        Optional<User> opt = this.existsByPhone(phone);
        if (!opt.isPresent()) {
            user = User.builder()
                    .city(data.getCity())
                    .name(data.getName())
                    .phone(phone)
                    .build();
            this.save(user);
            // 保存用户扩展信息
            UserExtension extension = UserExtension.builder()
                    .id(user.getId())
                    .email(data.getEmail())
                    .engName(data.getEngName())
                    .introduction(data.getIntr())
                    .build();
            extensionService.save(extension);
        } else {
            user = opt.get();
        }

        return this.convert(user);
    }

    @Override
    public UserInfoDTO updateUserBaseInfo(Integer id, UpdateUserDTO data) {
        User user = Optional.ofNullable(this.getById(id)).orElseThrow(() -> new RuntimeException("用户不存在"));
        user.setCity(data.getCity());
        user.setName(data.getName());
        // 数据库自动更新时间
        user.setUpdated(null);
        this.updateById(user);

        return this.convert(user);
    }

    @Override
    public UserInfoDTO getUserBaseInfo(Integer id) {
        return this.convert(Optional.ofNullable(this.getById(id)).orElseGet(User::new));
    }

    @Override
    public Page<UserInfoPageDTO> pageUserInfo(Integer pageNum, Integer pageSize) {
        Page<User> page = this.page(new Page<>(pageNum, pageSize));

        Page<UserInfoPageDTO> pageRet = new Page<>();
        BeanUtils.copyProperties(page, pageRet);
        pageRet.setRecords(page.getRecords().stream().map(x -> UserInfoPageDTO.builder().id(x.getId())
                .city(x.getCity()).name(x.getName()).phone(x.getPhone()).build()).collect(Collectors.toList()));

        return pageRet;
    }

    @Override
    public Page<UserDetailInfoPageDTO> pageUserDetailInfo(Integer pageNum, Integer pageSize) {
        Page<User> page = this.page(new Page<>(pageNum, pageSize));

        return baseMapper.pageUserDetailInfo(page);
    }

    private Optional<User> existsByPhone(String phone) {

        return Optional.ofNullable(this.getOne(Wrappers.<User>lambdaQuery().eq(User::getPhone, phone)));
    }

    private UserInfoDTO convert(User user) {
        return UserInfoDTO.builder()
                .city(user.getCity())
                .name(user.getName())
                .phone(user.getPhone())
                .build();
    }
}
