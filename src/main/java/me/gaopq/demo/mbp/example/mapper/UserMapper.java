package me.gaopq.demo.mbp.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.gaopq.demo.mbp.example.dto.user.UserDetailInfoPageDTO;
import me.gaopq.demo.mbp.example.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * User mapper
 *
 * @author gaopq
 * @date 2020 /4/17 18:28
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * Page user detail info page
     *
     * @param page page
     * @return the page
     */
    @Select("select u.id, u.name, u.city, u.phone, ue.email, ue.introduction, ue.eng_name as engName from user u " +
            "left join user_extension ue on u.id = ue.id order by u.id asc")
    Page<UserDetailInfoPageDTO> pageUserDetailInfo(Page<User> page);
}
