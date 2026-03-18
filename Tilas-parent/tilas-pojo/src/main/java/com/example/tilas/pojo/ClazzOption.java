package com.example.tilas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzOption {
    private List ClazzList; //班级统计
    private List dataList; //班级人员统计
}

