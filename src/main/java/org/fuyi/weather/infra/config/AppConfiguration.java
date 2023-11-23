package org.fuyi.weather.infra.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class AppConfiguration {

    private static String TEST_SQL = "SELECT 1";

    @Autowired
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute(TEST_SQL);
        return jdbcTemplate;
    }

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        /**
         * 避免source中的null值覆盖destination中的匹配属性
         **/
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setFullTypeMatchingRequired(true)
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

}
