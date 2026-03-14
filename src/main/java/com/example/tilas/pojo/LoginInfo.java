package com.example.tilas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
   *封装登录结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class LoginInfo {
    private Integer id;
    private String userName;
    private String token;
    private String name;
}
