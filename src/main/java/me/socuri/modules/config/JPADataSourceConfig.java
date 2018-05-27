package me.socuri.modules.config;

import me.socuri.modules.config.type.DataSourceType;
import me.socuri.modules.config.type.RoutingDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "me.socuri.modules",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
public class JPADataSourceConfig {
    private static final Logger log = LoggerFactory.getLogger(JPADataSourceConfig.class);


    @Autowired
    private JpaProperties jpaProperties;

    @Primary
    @Bean(name="masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource-secondary")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        RoutingDataSource routingDataSource=new RoutingDataSource();
        log.info("Configuring routeDataSource");

        Map<Object,Object> map=new HashMap<>();
        map.put(DataSourceType.MASTER,masterDataSource());
        map.put(DataSourceType.SLAVE,slaveDataSource());
        routingDataSource.setTargetDataSources(map);
        routingDataSource.setDefaultTargetDataSource(masterDataSource());
        return routingDataSource;
    }



    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("dataSource") DataSource dataSource) {

        return builder
                .dataSource(dataSource())
                .properties(jpaProperties.getHibernateProperties(dataSource()))
                .persistenceUnit("spg")
                .packages("me.socuri.modules")
                .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
