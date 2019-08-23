package com.mybatis.demo.service;

import com.mybatis.demo.entity.SystemVariable;

/**
 * @program: mybatis-demo
 * @description:
 * @author: Liu.Jie
 * @create: 2019-08-22 09:44
 **/
public interface SystemVariableService {

    SystemVariable queryByCode(String code);

    SystemVariable queryAll();
}
