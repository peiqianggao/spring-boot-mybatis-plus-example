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
@ApiModel("用户扩展信息")
@TableName(value = "user_extension")
public class UserExtension implements Serializable {

    @ApiModelProperty("用户id, 单表自增")
    @TableId(type = IdType.INPUT)
    private Integer id;

    @TableField(value = "`email`")
    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("简介")
    private String introduction;

    @ApiModelProperty("英文名称")
    @TableField(value = "`eng_name`")
    private String engName;

    @ApiModelProperty("创建记录时间")
    private Date created;

    @ApiModelProperty("更新记录时间")
    private Date updated;
}
