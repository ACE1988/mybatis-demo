package com.mybatis.demo.dao;

import com.mybatis.demo.entity.SystemVariable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: mybatis-demo
 * @description:
 * @author: Liu.Jie
 * @create: 2019-08-22 09:40
 **/
public interface SystemVariableMapper {

    SystemVariable queryByCode(@Param("code") String coe);

    List<SystemVariable> queryAll ();
}
