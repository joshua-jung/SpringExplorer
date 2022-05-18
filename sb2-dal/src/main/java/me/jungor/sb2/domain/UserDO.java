package me.jungor.sb2.domain;

import lombok.Data;
import me.jungor.common.bean.BaseDomain;
import me.jungor.sb2.constant.UserSexEnum;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by joshua on 29/03/2018.
 */
@Data
@Table(name = "t_users")
public class UserDO extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private String name;

    @Column
    private UserSexEnum sex;

}
