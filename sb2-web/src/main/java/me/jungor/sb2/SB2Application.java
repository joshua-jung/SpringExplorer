package me.jungor.sb2;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by joshua on 11/02/2018.
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("me.jungor.sb2.mapper")
public class SB2Application {

    @Bean
    public ExitCodeGenerator exitCodeGenerator2() {
        return new ExitCodeGenerator() {
            public int getExitCode() {
                return 43;
            }
        };
    }

    public static void main(String[] args) {
//        System.exit(SpringApplication.exit(SpringApplication.run(SB2Application.class, args)));  // Exit Test
        SpringApplication.run(SB2Application.class, args);  // Normal run
    }

}
