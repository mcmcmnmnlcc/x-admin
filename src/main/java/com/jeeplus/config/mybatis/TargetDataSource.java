package com.jeeplus.config.mybatis;

import java.lang.annotation.*;

/**
 * @Author caiChaoqi
 * @Date 2018-06-23
 * @Description 作用于类、接口或者方法上
 * @TargetDataSource(name = "ds1") 这个注解可以切换数据源，在service上面加
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

    String name();
}
