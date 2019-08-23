package com.mybatis.demo;

import com.github.pagehelper.PageHelper;
import com.mybatis.demo.entity.SystemVariable;
import com.mybatis.demo.service.SystemVariableService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @program: mybatis-demo
 * @description:
 * @author: Liu.Jie
 * @create: 2019-08-22 09:49
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest{

    @Autowired
    SystemVariableService systemVariableService ;

    @Test
    public void querySystemVariable(){
        SystemVariable systemVariable =  systemVariableService.queryByCode("KEY_CODE");
        Assert.assertTrue(systemVariable.getSystemId().equals("shutao"));
    }

    public void pageTest(){
        PageHelper.offsetPage(1, 10);
        List<SystemVariable> list = systemVariableService.queryAll(1);
    }
}
