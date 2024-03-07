package ru.astra.products.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DbConfig extends HikariConfig {

    private Environment environment;

    public DbConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        var hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        hikariConfig.setJdbcUrl(environment.getProperty("spring.datasource.url"));
        hikariConfig.setUsername(environment.getProperty("spring.datasource.hikari.username"));
        hikariConfig.setPassword(environment.getProperty("spring.datasource.hikari.password"));
        hikariConfig.setSchema(environment.getProperty("spring.datasource.hikari.schema"));
        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setMinimumIdle(2);

        return new HikariDataSource(hikariConfig);
    }
}
