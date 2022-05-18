package me.jungor.sb2.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by joshua on 13/02/2018.
 */
@Data
@Component
public class ApplicationProperties {

    @Value("${name}")
    private String name;

    @Value("${gender}")
    private String gender;

}
