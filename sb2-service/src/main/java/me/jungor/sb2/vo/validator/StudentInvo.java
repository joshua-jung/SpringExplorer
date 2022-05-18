package me.jungor.sb2.vo.validator;

import lombok.Data;
import me.jungor.common.validation.constraints.Exclude;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by joshua on 2018/5/27.
 */
@Data
public class StudentInvo {

    @NotEmpty(message = "Student name is null")
    private String name;

    @NotEmpty()
    private String sex;

    @Min(2)
    private Integer age;

    private Integer number;

    @Exclude({"a", "b"})
    private String letter;

}
