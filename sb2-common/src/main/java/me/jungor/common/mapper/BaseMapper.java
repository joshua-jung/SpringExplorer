package me.jungor.common.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by joshua on 11/04/2018.
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
