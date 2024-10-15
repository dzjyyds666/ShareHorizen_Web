package com.Aaron.entity.dto;

import lombok.Data;

@Data
public class CaptchaDto {
    private String img;
    private String code;

    public CaptchaDto(String img, String code)
    {
        this.img = img;
        this.code = code;
    }
}
