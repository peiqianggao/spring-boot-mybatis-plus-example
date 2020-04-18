package me.gaopq.demo.mbp.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.gaopq.demo.mbp.example.dto.user.*;
import me.gaopq.demo.mbp.example.entity.User;

import java.util.List;

/**
 * User service
 *
 * @author gaopq
 * @date 2020 /4/17 18:28
 */
public interface UserService extends IService<User> {

    /**
     * Add user user info dto
     *
     * @param data data
     * @return the user info dto
     */
    UserInfoDTO addUser(AddUserDTO data);

    /**
     * Update user base info user info dto
     *
     * @param id   id
     * @param data data
     * @return the user info dto
     */
    UserInfoDTO updateUserBaseInfo(Integer id, UpdateUserDTO data);

    /**
     * Gets user base info *
     *
     * @param id id
     * @return the user base info
     */
    UserInfoDTO getUserBaseInfo(Integer id);

    /**
     * Page user info page
     *
     * @param pageNum  page num
     * @param pageSize page size
     * @return the page
     */
    Page<UserInfoPageDTO> pageUserInfo(Integer pageNum, Integer pageSize);

    /**
     * Page user detail info page
     *
     * @param pageNum  page num
     * @param pageSize page size
     * @return the page
     */
    Page<UserDetailInfoPageDTO> pageUserDetailInfo(Integer pageNum, Integer pageSize);
}
