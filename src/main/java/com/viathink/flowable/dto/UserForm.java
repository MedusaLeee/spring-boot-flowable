package com.viathink.flowable.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.*;

@Data
public class UserForm {
    @NotBlank(message = "id不能为空")
    private String id;
    @NotBlank(message = "name不能为空")
    @Length(min = 2, max = 30)
    private String name;
    @Email(message = "email格式不对")
    private String email;
}
