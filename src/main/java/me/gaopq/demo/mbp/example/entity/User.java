package me.gaopq.demo.mbp.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gaopq
 * @date 2020/4/17 18:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户信息")
@TableName(value = "user")
public class User implements Serializable {

    @ApiModelProperty("用户id, 单表自增")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "`name`")
    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("创建记录时间")
    private Date created;

    @ApiModelProperty("更新记录时间")
    private Date updated;
}
