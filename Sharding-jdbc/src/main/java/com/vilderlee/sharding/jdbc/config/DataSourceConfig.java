package com.vilderlee.sharding.jdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 类说明:
 *
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2019/10/17      Create this file
 * </pre>
 */
@Configuration
public class DataSourceConfig {

    @Value("${spring.shardingsphere.sharding.tables.orders.actualDataNodes}")
    private String ordersActualDataNodes;
    @Value("${spring.shardingsphere.sharding.tables.orders_detail.actualDataNodes}")
    private String ordersDetailActualDataNodes;
    @Value("${spring.shardingsphere.sharding.tables.orders.databaseStrategy.inline.shardingColumn}")
    private String databaseOrderShardingColumn;
    @Value("${spring.shardingsphere.sharding.tables.orders.tableStrategy.inline.shardingColumn}")
    private String tableOrderShardingColumn;

    /**
     * 订单逻辑表
     */
    private String ordersLogicTable = "orders";
    /**
     * 订单详情逻辑表
     */
    private String ordersDetailLogicTable = "orders_detail";


    @Autowired
    private StandDatabasePreciseShardingAlgorithm standDatabasePreciseShardingAlgorithm;
    @Autowired
    private StandDatabasePreciseShardingAlgorithm1 standDatabasePreciseShardingAlgorithm1;

    @Autowired
    private StandTableShardingAlgorithm standTableShardingAlgorithm;

    @Bean
    public DataSource dataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();

        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(
                new StandardShardingStrategyConfiguration(ordersLogicTable,
                        standDatabasePreciseShardingAlgorithm1));
        shardingRuleConfig.getBindingTableGroups().add(ordersLogicTable);
        shardingRuleConfig.getBindingTableGroups().add(ordersDetailLogicTable);
        //配置Orders表规则
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        //配置ordersItem表规则
        shardingRuleConfig.getTableRuleConfigs().add(getOrderDetailTableRuleConfiguration());

        shardingRuleConfig.setDefaultDataSourceName("shard_order_0");
        Properties properties = new Properties();
        properties.put("sql.show", true);
        return ShardingDataSourceFactory.createDataSource(getDataSourceMap(), shardingRuleConfig, properties);
    }

    private Map<String, DataSource> getDataSourceMap() {
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setUrl("");
        dataSource1.setUsername("root");
        dataSource1.setPassword("123456");
        dataSourceMap.put("shard_order_0", dataSource1);

        DruidDataSource dataSource2 = new DruidDataSource();
        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource2.setUrl("");
        dataSource2.setUsername("root");
        dataSource2.setPassword("123456");
        dataSourceMap.put("shard_order_1", dataSource2);

        return dataSourceMap;
    }

    private TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration(ordersLogicTable,
                ordersActualDataNodes);
//        tableRuleConfiguration.setDatabaseShardingStrategyConfig(
//                new StandardShardingStrategyConfiguration(databaseOrderShardingColumn,
//                        standDatabasePreciseShardingAlgorithm));
        tableRuleConfiguration
                .setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration(tableOrderShardingColumn,
                        standTableShardingAlgorithm));
        return tableRuleConfiguration;
    }


    private TableRuleConfiguration getOrderDetailTableRuleConfiguration() {
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration(ordersDetailLogicTable,
                ordersDetailActualDataNodes);
        tableRuleConfiguration.setDatabaseShardingStrategyConfig(
                new StandardShardingStrategyConfiguration(databaseOrderShardingColumn,
                        standDatabasePreciseShardingAlgorithm));
        tableRuleConfiguration
                .setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration(tableOrderShardingColumn,
                        standTableShardingAlgorithm));
        return tableRuleConfiguration;
    }

    @Bean("shardSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(
                "classpath*:/mapper/sharding/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("shardSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws SQLException {
        return new JdbcTemplate(dataSource());
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String sql = "Select * FROM orders_0 WHERE id = 1 and JSON_UNQUOTE(JSON_EXTRACT(extra , '$.username')) = 'lichao' ";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager
                .getConnection("", "root", "123456");
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println();

    }

}
