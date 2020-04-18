package me.gaopq.demo.mbp.example.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author gaopq
 * @date 2020/4/17 19:38
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {

    @NotBlank
    private String name;

    @ApiModelProperty("手机号添加后暂不能修改")
    private String phone;

    @NotBlank
    private String city;
}
