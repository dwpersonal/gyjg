package com.zcreate.offline.gyjg.oracletomysql.service.mysql.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @auther a123
 * @create 2017-12-11 11:19
 * @desc 内容平台数据配置
 */
@Configuration
@MapperScan(basePackages = "com.zcreate.offline.gyjg.oracletomysql.service.mysql",
        sqlSessionTemplateRef = "mysqlTemlate")
public class DataSourceMysqlConfig {

    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    @Primary
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mysqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfigLocation(new ClassPathResource("mybatis.xml"));
        return bean.getObject();
    }

    @Bean(name = "mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager mysqlTransactionManager(@Qualifier("mysqlDataSource")
                                                                            DataSource dataSource) {
        return new DataSourceTransactionManager((dataSource));
    }

    @Bean(name = "mysqlTemlate")
    @Primary
    public SqlSessionTemplate mysqlTemlate(
            @Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory
    ) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}