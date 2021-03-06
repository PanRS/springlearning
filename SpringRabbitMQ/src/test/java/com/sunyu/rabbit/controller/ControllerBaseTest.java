package com.sunyu.rabbit.controller;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 *
 * controller测试基础类
 * @author sunyu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring-mybatis.xml",
        "classpath:spring-mvc.xml"
})
@WebAppConfiguration
public class ControllerBaseTest {

}