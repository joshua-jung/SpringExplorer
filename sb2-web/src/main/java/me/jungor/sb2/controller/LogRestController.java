package me.jungor.sb2.controller;

import lombok.extern.slf4j.Slf4j;
import me.jungor.common.exception.BusinessException;
import me.jungor.sb2.properties.ApplicationProperties;
import me.jungor.sb2.properties.ExamplePropertiesBean;
import me.jungor.sb2.vo.user.UserOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Example of properties and logger
 *
 * Created by joshua on 14/02/2018.
 */
@RestController
@Slf4j
@RequestMapping("/log")
public class LogRestController {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private ExamplePropertiesBean examplePropertiesBean;

    @GetMapping(value = "")
    public String index() {
        log.info("{} and {}", examplePropertiesBean.getChn(), examplePropertiesBean.getEng());
        log.warn("{} and {}", examplePropertiesBean.getChn(), examplePropertiesBean.getEng());
        log.error("{} and {}", applicationProperties.getName(), applicationProperties.getGender());

        return "log-index";
    }

    @GetMapping(value = "exception")
    public String exception() {
        throw new BusinessException("Exception Msg");
    }

    @GetMapping("nullpointer")
    public String NullPointer() {
        UserOutVo userOutVo = null;
        userOutVo.getName();
        return "Success";
    }

}
