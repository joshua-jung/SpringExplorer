package me.jungor.common.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import java.util.Date;

/**
 * Created by joshua on 12/04/2018.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseDomain extends BasePojo {

    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最近一次的修改时间
     */
    private Date updateTime;

    /**
     * 逻辑删除标志位
     *
     * @implNote 数据库对应字段必须notNull
     */
    private Boolean available;

    /**
     * 创建资源的用户
     */
    private String createUser;

    /**
     * 修改资源的用户
     */
    private String updateUser;

}
