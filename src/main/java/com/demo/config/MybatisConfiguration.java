package com.demo.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.SqlUtilConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@ConditionalOnClass({EnableTransactionManagement.class})
@MapperScan(basePackages = {"imall.mapper"})
public class MybatisConfiguration {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${mybatis.typeAliasesPackage:}")
    String modelPackage;//实体包

    @Autowired
    @Qualifier("pathMatchingResourcePatternResolver")
    ResourcePatternResolver resolver;

    @Autowired
    ApplicationContext applicationContext;

    @Value("${mybatis.mapperlocations:classpath*:mybatis/mapper/*/*.xml}")
    private String mapperLocations;//mapper 位置

    @Value("${mybatis.plugin.name:}")
    private String pluginName;//mybatis插件

    @Bean
    @ConfigurationProperties(prefix = "mybatis.plugin.pagehelper")
    public SqlUtilConfig sqlUtilConfig() {
        SqlUtilConfig sqlUtilConfig = new SqlUtilConfig();
        return sqlUtilConfig;
    }

    @Bean("pageHelper")
    public PageHelper pageHelper(SqlUtilConfig sqlUtilConfig) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.setSqlUtilConfig(sqlUtilConfig);
        return pageHelper;
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(modelPackage);
        sqlSessionFactoryBean.setPlugins(initPlugin());
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }

    private Interceptor[] initPlugin() {
        if (!StringUtils.isBlank(pluginName)) {
            String pluginNameArray[] = pluginName.split(",");
            Interceptor[] plugin = new Interceptor[pluginNameArray.length];
            int pluginCount = 0;

            for (String item : pluginNameArray) {
                plugin[pluginCount] = (Interceptor) applicationContext.getBean(item);

                log.debug("mybatis plugin " + plugin[pluginCount]);

                pluginCount++;
            }
            return plugin;
        }
        return new Interceptor[]{};
    }

    @Bean(destroyMethod = "close")
    @ConditionalOnMissingBean
    @Resource(name = "sqlSessionFactory")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }

}
