package me.jungor.sb2.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by joshua on 14/02/2018.
 */
@Configuration
@PropertySource(value = "classpath:/jdbc.properties")
public class DataSourceProperties {

    @Autowired
    Environment env;

//    @Bean
//    public DruidDataSource dataSource() {
//
//    }


}
