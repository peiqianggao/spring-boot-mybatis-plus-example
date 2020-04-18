package me.gaopq.demo.mbp.example.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author gaopq
 * @date 2020/4/17 19:20
 */
@Data
@ApiModel("添加用户")
public class AddUserDTO {

    @NotBlank
    @Size(max = 20, message = "用户名字数过多, 其他参数限制同理")
    private String name;

    @NotBlank
    @ApiModelProperty("手机号添加后暂不能修改")
    private String phone;

    @NotBlank
    private String city;

    @NotBlank
    private String email;

    @NotBlank
    private String intr;

    @NotBlank
    private String engName;
}
