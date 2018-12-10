package com.manage.frame.shiro.config;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/10
 * Time: 19:30
 */

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages  = "com.manage.frame.dao", sqlSessionTemplateRef  = "shiroSqlSessionTemplate")
public class ShiroDataSourceConfig {

//    @ConfigurationProperties(prefix = "spring.dataSource.druid.shiroDataSource")
//    @Bean(name = "shiroDataSource")
//    public DataSource shiroDataSource() throws SQLException {
//        return DruidDataSourceBuilder.create().build();
//    }

//    @Bean(name = "sessionFactoryShiro")
//    public SqlSessionFactory sessionFactoryShiro(@Qualifier(value = "shiroDataSource") DataSource dataSource,
//                                                 PaginationInterceptor paginationInterceptor,
//                                                 @Qualifier(value="globalConfigurationShiro")GlobalConfiguration globalConfiguration) throws Exception{
//        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:shiroMapper/*.xml"));
//        Interceptor[] interceptors = new Interceptor[]{paginationInterceptor};
//        bean.setPlugins(interceptors);
//        bean.setGlobalConfig(globalConfiguration);
//        return bean.getObject();
//    }

//    @ConfigurationProperties(prefix = "globalConfigShrio")
//    @Bean(name="globalConfigurationShiro")
//    public GlobalConfiguration globalConfigurationShiro() {
//        return new GlobalConfiguration();
//    }
//
//    @Bean(name = "transactionManagerShiro")
//    public DataSourceTransactionManager dataSourceTransactionManager2(@Qualifier("shiroDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "shiroSqlSessionTemplate")
//    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sessionFactoryShiro") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
}
