package me.jungor.sb2.controller;

import lombok.extern.slf4j.Slf4j;
import me.jungor.sb2.vo.test.UserTestInVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC Controller 获取请求数据的方式
 *
 * Created by joshua on 2018/5/23.
 */
@RestController
@Slf4j
@RequestMapping("/param")
@SessionAttributes(value = "userName")
public class ParamController {

    /**
     * 此 Controller 中的访问都会出发此方法，此方法可以请求中的参数
     *
     * @param name
     * @return
     */
    @ModelAttribute("userAddress")
    private String bindUserAddress(String name) {
        log.info("Bind {}'s address", name);
        return String.format("%s in Chongming", name);
    }

    @RequestMapping("/path-test/{name}")
    public String pathTest(@PathVariable String name) {
        return String.format("%s is handsome.", name);
    }

    @RequestMapping("/request-header-test")
    public String requestHeaderTest(@RequestHeader("Accept-Encoding") String encoding,
                                    @RequestHeader("Connection") String conection) {
        return String.format("Accepting-Encoding is %s and Connection is %s", encoding, conection);
    }

    @RequestMapping("/cookie-value-test")
    public String cookieValueTest(@CookieValue String cna) {
        return String.format("cna in Cookie is %s", cna);
    }

    /**
     * RequestParam 获取 queryString 和 Post 表单中数据，表单类型必须为 application/x-www-form-urlencoded
     */
    @RequestMapping("/request-param-test")
    public String requestParamTest(@RequestParam String name) {
        return String.format("%s is smart.", name);
    }

    /**
     * RequestBody 获取非 application/x-www-form-urlencoded 类型的请求体内的数据，比如 application/json、application/xml
     */
    @RequestMapping("/request-body-test")
    public String requestBodyTest(@RequestBody UserTestInVO userTestInVO) {
        return String.format("%s is smart.", userTestInVO.getName());
    }

    /**
     * 会报 404，不影响绑定到 session
     *
     * @return
     */
    @RequestMapping("/session-attr-add-test")
    public ModelAndView sessionAttrAddTest() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", "Joshua");
        return modelAndView;
    }

    /**
     * userName 的值由 SessionAttribute 绑入，非空需先访问 @link sessionAttrAddTest
     * userAddress 由 @link bindUserAddress 帮入
     * sex 是请求参数
     *
     * @param userName
     * @return
     */
    @RequestMapping("/model-attr-test")
    public String sessionAttrTest(@ModelAttribute("userName") String userName,
                                  @ModelAttribute("userAddress") String userAddress,
                                  @ModelAttribute("sex") String sex) {
        return String.format("%s - %s with Address: %s", userName, sex, userAddress);
    }

}
