package com.example.tilas.mapper;

import com.example.tilas.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    //插入日志数据
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime})")
    void insert(OperateLog log);

    //日志回显
    @Select("select ol.id, ol.operate_emp_id, e.name operate_emp_name, ol.operate_time, ol.class_name, " +
            "ol.method_name, ol.method_params, ol.return_value, ol.cost_time " +
            "from operate_log ol left join emp e on e.id = ol.operate_emp_id " +
            "order by ol.operate_time desc, ol.id desc")
    List<OperateLog> list();

}
