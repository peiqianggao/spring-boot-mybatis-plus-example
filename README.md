# SpringBoot集成mybatis-plus

#### 代码仓库
- [Github](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html)
- [Gitee](https://www.cnblogs.com/wuyuegb2312/p/11172440.html)

#### 项目结构
![](https://i.loli.net/2020/04/18/4sLMAdnGwSHCjDx.png)

#### 项目依赖
- 开发框架 springboot
  - 2.2.6.RELEASE
- 接口文档 swagger-spring-boot-starter
  - 1.9.1.RELEASE
- 数据库驱动 mysql-connector-java
  - 8.0.15
- mybatis-plus-boot-starter
  - 3.3.1.tmp
- lombok

#### 数据准备
- 创建数据库(本案例使用 `Docker` 部署的MySQL5.7)
```mysql
create database `mybatis_plus_example` character set utf8mb4 collate utf8mb4_general_ci;
```
- 创建用户并授权
```mysql
create user 'test'  identified by 'xxx_xxx2020';
grant all on `mybatis_plus_example`.* to test@'%';
```
- 建表
```mysql
CREATE TABLE `user` (
    `id`      int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`    varchar(32)      NOT NULL COMMENT '用户名',
    `phone`   varchar(32)      NOT NULL DEFAULT '' COMMENT '手机号',
    `city`    varchar(32)      NOT NULL COMMENT '城市',
    `created` datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated` datetime                  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uidx_phone` (`phone`)
) COMMENT ='用户信息' AUTO_INCREMENT = 1000000;

CREATE TABLE `user_extension` (
    `id`           int(11) UNSIGNED NOT NULL COMMENT '用户id, user.id',
    `email`        varchar(32)      NOT NULL DEFAULT '' COMMENT '邮箱',
    `introduction` varchar(512)     NOT NULL DEFAULT '' COMMENT '用户简介',
    `eng_name`     varchar(32)      NOT NULL COMMENT '英文名称',
    `created`      datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated`      datetime                  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) COMMENT ='用户扩展信息';
```

#### 配置类
- `MybatisPlusConfig` 注入分页插件
```java
package me.gaopq.demo.mbp.example.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author gaopq
 * @date 2020/4/17 17:35
 */
@Configuration
@EnableTransactionManagement
@MapperScan("me.gaopq.demo.mbp.example.mapper")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {

        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        paginationInterceptor.setLimit(500);
        paginationInterceptor.setDbType(DbType.MYSQL);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));

        return paginationInterceptor;
    }
}

```

#### 逻辑层
- 创建 `User` 实体类, `UserExtension` 实体类可参考 `User` 编写, 其他类同理
```java
package me.gaopq.demo.mbp.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gaopq
 * @date 2020/4/17 18:22
 */
@Data
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

```
- 创建 `UserMapper` 接口
```java
package me.gaopq.demo.mbp.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.gaopq.demo.mbp.example.entity.User;

/**
 * @author gaopq
 * @date 2020/4/17 18:28
 */
public interface UserMapper extends BaseMapper<User> {
}
```

#### 分页实现
- `User` 表内分页

   ```
    @Override
    public Page<UserInfoPageDTO> pageUserInfo(Integer pageNum, Integer pageSize) {
        Page<User> page = this.page(new Page<>(pageNum, pageSize));

        Page<UserInfoPageDTO> pageRet = new Page<>();
        BeanUtils.copyProperties(page, pageRet);
        pageRet.setRecords(page.getRecords().stream().map(x -> UserInfoPageDTO.builder().id(x.getId())
                .city(x.getCity()).name(x.getName()).phone(x.getPhone()).build()).collect(Collectors.toList()));

        return pageRet;
    } 
   ```

- `User` 和 `UserExtension` 联表分页

    ```
    @Select("select u.id, u.name, u.city, u.phone, ue.email, ue.introduction, ue.eng_name as engName from user u " +
            "left join user_extension ue on u.id = ue.id order by u.id asc")
    Page<UserDetailInfoPageDTO> pageUserDetailInfo(Page<User> page);
    ```

#### 操作示例
- 启动项目, 打开接口文档地址: http://127.0.0.1:8088/swagger-ui.html
- 添加多个用户
![](https://i.loli.net/2020/04/18/KIEc49okMVYt2BA.png)
- 表内分页查询

    ```
    curl -X GET "http://127.0.0.1:8088/api/user/info?pageNum=1&pageSize=5" -H "accept: */*"
    ```
- 跨表查询
![](https://i.loli.net/2020/04/18/k5CYjZl84gc1itq.png)

#### 注意要点
- 字段命名使用了数据库关键字如 `from`, `where` 的时候, 实体类中属性注解 `@TableField` 中应使用 `` 对字段名做转义

- mybatis(Plus) 封装的分页类 `Page` 默认开始值为 1, 如果业务上分页从 0 开始则需要另外转换页码

- 分页查询需要注入分页插件 `PaginationInterceptor` 才能生效, 否则会默认按不分页按条件查出所有结果

- mybatis-plus 一般情况下不需要另外创建 `xxxMapper.xml`, 如特殊情况需要通过 xml 自定义 sql 时, 需要在配置文件通过 `mybatis-plus.mapper-locations` 指定 xml 的扫描路径

####  官方文档
- [mybatis-plus](https://github.com/baomidou/mybatis-plus)
- [mybatis-plus 使用手册](https://mp.baomidou.com/)
