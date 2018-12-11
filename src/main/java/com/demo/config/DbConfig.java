package com.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableTransactionManagement
@Configuration
public class DbConfig {
    private Logger log =  LoggerFactory.getLogger(this.getClass());

    /**
     * 自定义事务 MyBatis自动参与到spring事务管理中，无需额外配置，只要org.mybatis.spring.
     * SqlSessionFactoryBean引用的数据源与DataSourceTransactionManager引用的数据源一致即可
     * ，否则事务管理会不起作用。
     *
     */
    @Autowired
    private DataSource dataSource;

    @Bean(name = "transactionManager")
    public org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManagers() {
        log.info("-------------------- transactionManager init ---------------------");
        return new org.springframework.jdbc.datasource.DataSourceTransactionManager(dataSource);
    }
}
