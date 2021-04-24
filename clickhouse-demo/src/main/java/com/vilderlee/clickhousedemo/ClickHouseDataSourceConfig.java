package com.vilderlee.clickhousedemo;

import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
@MapperScan(basePackages = {"com.vilderlee.clickhousedemo"},
        sqlSessionFactoryRef = "clickHouseSqlSessionFactoryBean")
@ConfigurationProperties(prefix = "clickhouse.jdbc.datasource")
public class ClickHouseDataSourceConfig {

    private String username;

    private String driverName;

    private String password;

    private String url;

    private String type;

    private long maxWait;

    private String validationQuery;


    @Bean
    public DataSource clickhouseDataSource() throws Exception {
        Class classes = Class.forName(type);
        DruidDataSource dataSource = (DruidDataSource) DataSourceBuilder
                .create()
                .driverClassName(driverName)
                .type(classes)
                .url(url)
                .username(username)
                .password(password)
                .build();
        dataSource.setMaxWait(maxWait);
        dataSource.setValidationQuery(validationQuery);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory clickHouseSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(clickhouseDataSource());
        //添加XML目录
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        factory.setMapperLocations(resolver.getResources("classpath:mapper/clickhouse/*.xml"));
        //开启驼峰命名转换
        factory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return factory.getObject();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }
}
