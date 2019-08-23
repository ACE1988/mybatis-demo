package com.mybatis.demo.service.impl;

import com.mybatis.demo.dao.SystemVariableMapper;
import com.mybatis.demo.entity.SystemVariable;
import com.mybatis.demo.service.SystemVariableService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: mybatis-demo
 * @description:
 * @author: Liu.Jie
 * @create: 2019-08-22 09:45
 **/
@Component
public class SystemVariableServiceImpl implements SystemVariableService {

    @Resource
    SystemVariableMapper systemVariableMapper;

    @Override
    public SystemVariable queryByCode(String code) {
        return systemVariableMapper.queryByCode(code);
    }

    @Override
    public SystemVariable queryAll() {
        return null;
    }
}
