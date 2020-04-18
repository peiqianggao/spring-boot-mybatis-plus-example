package me.gaopq.demo.mbp.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import me.gaopq.demo.mbp.example.entity.UserExtension;
import me.gaopq.demo.mbp.example.mapper.UserExtensionMapper;
import me.gaopq.demo.mbp.example.service.UserExtensionService;
import org.springframework.stereotype.Service;

/**
 * @author gaopq
 * @date 2020/4/17 23:00
 */
@Slf4j
@Service
public class UserExtensionServiceImpl extends ServiceImpl<UserExtensionMapper, UserExtension> implements UserExtensionService {

}
