package com.example.tilas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * emp查询参数
 *
 * @author Administrator
 * @date 2026/02/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpQueryParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String name;
    private Integer gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
}

