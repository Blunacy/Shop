package com.lunacy.shop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MemberFormDto {
  @NotBlank(message = "이름은 필수 입력입니다.")
  private String name;

  @NotEmpty(message = "이메일은 필수 입력 값입니다.")
  @Email(message = "이메일 형식으로 입력해주세요.")
  private String email;

  @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
  @Length(min = 8, max = 16, message = "비밀번호는 8자 이상 16자 이하로 입력해주세요.")
  private String password;

  @NotEmpty(message = "주소는 필수 입력 값입니다.")
  private String address;
}
