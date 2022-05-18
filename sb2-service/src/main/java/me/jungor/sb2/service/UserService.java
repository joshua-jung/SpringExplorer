package me.jungor.sb2.service;

import com.github.pagehelper.PageInfo;
import me.jungor.sb2.domain.UserDO;
import me.jungor.sb2.vo.user.UserInVo;
import me.jungor.sb2.vo.user.UserPageInVo;

/**
 * Created by joshua on 12/04/2018.
 */
public interface UserService {

    PageInfo<UserDO> queryUserList(UserPageInVo userPageInVo);

    UserDO detail(Long userId);

    void saveUser(UserInVo userInVo);

}
