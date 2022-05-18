package me.jungor.sb2.vo.user;

import lombok.Data;
import me.jungor.sb2.constant.UserSexEnum;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by joshua on 13/04/2018.
 */
@Data
public class UserInVo {

    @NotBlank
    private String name;

    private UserSexEnum sex;

}
