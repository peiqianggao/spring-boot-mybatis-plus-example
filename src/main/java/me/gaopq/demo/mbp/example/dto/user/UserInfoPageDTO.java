package me.gaopq.demo.mbp.example.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gaopq
 * @date 2020/4/17 19:38
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoPageDTO {

    private Integer id;

    private String name;

    private String phone;

    private String city;
}
