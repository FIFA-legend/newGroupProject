package by.clowns.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("by.clowns.dao")
@PropertySource("classpath:configuration.properties")
public class DaoConfiguration {

    @Value("${database.url}")
    private String databaseUrl;

    @Value("${database.driver}")
    private String databaseDriver;

    @Value("${database.user}")
    private String databaseUser;

    @Value("${database.password}")
    private String databasePassword;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.show_SQL}")
    private String hibernateShowSQL;

    @Value("${hibernate.format}")
    private String hibernateFormat;

    @Value("${hibernate.policy}")
    private String hibernatePolicy;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(databaseUrl);
        dataSource.setDriverClassName(databaseDriver);
        dataSource.setUsername(databaseUser);
        dataSource.setPassword(databasePassword);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setPackagesToScan("by.clowns.entity");
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    public Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", hibernateDialect);
        properties.setProperty("hibernate.show_sql", hibernateShowSQL);
        properties.setProperty("hibernate.format_sql", hibernateFormat);
        properties.setProperty("hibernate.hbm2ddl_auto", hibernatePolicy);
        return properties;
    }
}
