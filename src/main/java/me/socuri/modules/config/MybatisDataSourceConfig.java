package me.socuri.modules.config;

import javax.sql.DataSource;

import me.socuri.modules.config.type.DataSourceType;
import me.socuri.modules.config.type.RoutingDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(value="me.socuri.modules", sqlSessionFactoryRef="sqlSessionFactory")
@EnableTransactionManagement
public class MybatisDataSourceConfig {
    private Logger logger= LoggerFactory.getLogger(MybatisDataSourceConfig.class);

    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource-secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mybatisDataSource")
    public DataSource dataSource() {
        RoutingDataSource routingDataSource=new RoutingDataSource();
        logger.info("Configuring routeDataSource");

        Map<Object,Object> map=new HashMap<>();
        map.put(DataSourceType.MYPRIMARY,primaryDataSource());
        map.put(DataSourceType.MYSECONDARY,secondaryDataSource());
        routingDataSource.setTargetDataSources(map);
        routingDataSource.setDefaultTargetDataSource(primaryDataSource());
        return routingDataSource;
    }


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mybatisDataSource") DataSource mybatisDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(mybatisDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("me.socuri.modules");
        sqlSessionFactoryBean.setTypeHandlersPackage("me.socuri.modules");
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {

        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
