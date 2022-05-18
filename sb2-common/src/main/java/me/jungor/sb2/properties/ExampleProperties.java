package me.jungor.sb2.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by joshua on 14/02/2018.
 */
@Configuration
@PropertySource(value = "classpath:/example.properties")
public class ExampleProperties {

    @Autowired
    private Environment env;

    @Bean
    public ExamplePropertiesBean exampleConfiguration() {
        ExamplePropertiesBean exampleConfiguration = new ExamplePropertiesBean();
        exampleConfiguration.setChn(env.getProperty("chn"));
        exampleConfiguration.setEng(env.getProperty("eng"));

        return exampleConfiguration;
    }


}
