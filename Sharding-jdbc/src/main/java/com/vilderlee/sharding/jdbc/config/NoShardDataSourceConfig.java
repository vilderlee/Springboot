package com.vilderlee.sharding.jdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.vilderlee.sharding.jdbc.mapper.noshard.ShardConfigMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 类说明:
 *
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2019/10/18      Create this file
 * </pre>
 */
@Configuration("noShardDataSourceConfig")
public class NoShardDataSourceConfig {

    @Bean
    public DataSource noShardDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://9.139.4.29:8080/json_test");
        dataSource.setUsername("apaas");
        dataSource.setPassword("wJ2i0ybZUxN88U@#");
        return dataSource;
    }


    @Bean("noShardSqlSessionFactory")
    public SqlSessionFactory noSqlSessionFactory(DataSource noShardDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(noShardDataSource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(
                "classpath*:/mapper/nosharding/*.xml"));
        return sqlSessionFactory.getObject();
    }

    @Bean("noSqlSessionTemplate")
    public SqlSessionTemplate noSqlSessionTemplate(@Qualifier("noShardSqlSessionFactory") SqlSessionFactory noSqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(noSqlSessionFactory);
    }
}
