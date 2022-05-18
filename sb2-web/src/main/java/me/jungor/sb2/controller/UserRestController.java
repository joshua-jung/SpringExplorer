package me.jungor.sb2.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import me.jungor.sb2.domain.UserDO;
import me.jungor.sb2.service.UserService;
import me.jungor.sb2.vo.user.UserInVo;
import me.jungor.sb2.vo.user.UserPageInVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Test about Rest
 *
 * Created by joshua on 19/03/2018.
 */
@RestController
@Slf4j
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageInfo<UserDO> getUser(UserPageInVo userPageInVo) {
        return userService.queryUserList(userPageInVo);
    }

    @GetMapping(value = "/{userId}")
    public UserDO getUser(@PathVariable Long userId) {
        return userService.detail(userId);
    }

    @PostMapping
    public void addUser(UserInVo userInVo) {
        userService.saveUser(userInVo);
    }

}
