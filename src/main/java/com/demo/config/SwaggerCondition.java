package com.demo.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Desc:指定swagger api 生成的条件:非生产环境生成
 * <p>
 * Created by yclimb on 2017/4/19.
 */
public class SwaggerCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return !"prd".equals(context.getEnvironment().getProperty("spring.profiles.active"));
    }

}