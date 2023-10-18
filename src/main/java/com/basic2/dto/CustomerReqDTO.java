package com.basic2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerReqDTO {

@NotEmpty(message = "Name은 필수 입력항목입니다.") // " " 이게허용됨
private String name;
@NotBlank(message = "Email은 필수 입력항목입니다.")// " " 허용하지 않음 자체적으로 trim하기 때문에
@Email
private String email;

}
