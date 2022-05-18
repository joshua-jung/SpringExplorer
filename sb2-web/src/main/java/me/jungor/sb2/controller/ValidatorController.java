package me.jungor.sb2.controller;

import lombok.extern.slf4j.Slf4j;
import me.jungor.common.validation.constraints.Exclude;
import me.jungor.sb2.vo.validator.StudentInvo;
import org.hibernate.validator.constraints.Email;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * JSR 303 Bean Validation
 * <p>
 * Reference : https://www.jianshu.com/p/2a9bd377e563
 * <p>
 * Created by joshua on 2018/5/27.
 */
@RestController
@Slf4j
@RequestMapping("/validator")
public class ValidatorController {

    @GetMapping("/test")
    public String test(@Valid StudentInvo studentInvo) {
        return studentInvo.getName() != null ? studentInvo.getName() : "Oops";
    }

    /**
     * 没起作用（待探索 todo）
     *
     * @param mail
     * @return
     */
    @GetMapping("/test-simple")
    public String testSimple(@Valid @Email @RequestParam("mail") String mail) {
        return mail != null ? mail : "--";
    }

}
