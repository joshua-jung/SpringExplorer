package me.jungor.sb2.web.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.jungor.common.constant.BaseRestCode;

/**
 * Created by joshua on 2018/6/8.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResponseEntity {

    private Enum code;

    private Object data;

    private String msg;

    public RestResponseEntity(Enum code) {
        this.code = code;
    }

    public RestResponseEntity(Object data) {
        this.code = BaseRestCode.S200;
        this.data = data;
    }

    public RestResponseEntity(Enum code, Object data) {
        this.code = code;
        this.data = data;
    }

}
