package me.gaopq.demo.mbp.example.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author gaopq
 * @date 2020/4/17 19:20
 */
@Data
@ApiModel("添加用户")
public class UpdateUserDTO {

    @NotBlank
    @ApiModelProperty("姓名")
    private String name;

    @NotBlank
    private String city;
}
