package me.jungor.sb2.controller;

import me.jungor.common.exception.BusinessException;
import me.jungor.sb2.vo.test.FakeOutVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Index
 *
 * Created by joshua on 14/02/2018.
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "exception")
    public String exception() {
        throw new BusinessException("Index Exception");
    }

    @RequestMapping("test-response-status")
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public String testResponseBodyFaild(){
        return "Test Response Status";
    }

    @RequestMapping("test-response-entity")
    public ResponseEntity<FakeOutVo> testResponseEntityFaild2(){
        return ResponseEntity.ok(new FakeOutVo() {{
            setNothing("Nothing");
            setEverything("Everything");
        }});
    }

}
