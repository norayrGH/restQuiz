package com.rest.quiz.restQuiz.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class H2TestProfileJPAConfig {
    final DataSource dataSource;

    @Autowired
    public H2TestProfileJPAConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    @Profile("test")
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.xml");
        liquibase.setDataSource(dataSource);
        liquibase.setDefaultSchema("TESTSCHEMA");
        liquibase.setLiquibaseTablespace("testdb");
        return liquibase;
    }
}
