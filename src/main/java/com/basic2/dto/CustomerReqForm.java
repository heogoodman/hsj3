package com.basic2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class CustomerReqForm {

    private Long id;

    @NotEmpty(message = "Name은 필수 입력항목입니다.")// " " 이게허용됨
    private String name;

    @NotBlank(message = "Email은 필수 입력항목입니다.")// " " 허용하지 않음 자체적으로 trim하기때문에
    @Email(message = "Email 형식이 아닙니다.")
    private String email;


}