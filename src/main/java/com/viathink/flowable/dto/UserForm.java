package com.viathink.flowable.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.*;

@Data
public class UserForm {
    @NotBlank(message = "不能为空")
    private String id;
    @NotBlank(message = "不能为空")
    @Length(min = 2, max = 30, message = "最小为2，最长30")
    private String name;
    @Email(message = "格式不对")
    private String email;
}
