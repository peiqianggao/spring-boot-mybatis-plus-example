package me.gaopq.demo.mbp.example.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("用户明细分页信息")
public class UserDetailInfoPageDTO {

    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("手机号添加后暂不能修改")
    private String phone;

    @ApiModelProperty("所在城市")
    private String city;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("简介")
    private String introduction;

    @ApiModelProperty("英文名称")
    private String engName;
}
