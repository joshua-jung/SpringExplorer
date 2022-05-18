package me.jungor.sb2.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import me.jungor.sb2.domain.UserDO;
import me.jungor.sb2.mapper.UserMapper;
import me.jungor.sb2.service.UserService;
import me.jungor.sb2.vo.user.UserInVo;
import me.jungor.sb2.vo.user.UserPageInVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by joshua on 12/04/2018.
 */
@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<UserDO> queryUserList(UserPageInVo userPageInVo) {
        PageHelper.startPage(userPageInVo.getPageNo(), userPageInVo.getPageSize());
        PageHelper.orderBy(userPageInVo.getOrderBys());
        return new PageInfo<>(userMapper.selectAll());
    }

    @Override
    public UserDO detail(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void saveUser(UserInVo userInVo) {
        userMapper.insert(new UserDO() {{
            setName(userInVo.getName());
            setSex(userInVo.getSex());
        }});
    }

}
