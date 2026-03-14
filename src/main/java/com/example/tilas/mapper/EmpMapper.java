package com.example.tilas.mapper;

import com.example.tilas.pojo.Emp;
import com.example.tilas.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;
import java.util.Map;

/*
 *员工管理
 */
@Mapper
public interface EmpMapper {
    //----------------------------------------原始分页查询方式
    /*
     *查询总记录数
     */
//    @Select("select count(*) from emp e left join  dept  d on e.id=d.id")
//    public Long count();
//
//    /*
//     *分页查询
//     */
//    @Select("select e.*,d.name deptName from emp e left join  dept  d on e.dept_id=d.id  " +
//            "order by e.update_time desc limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);

//    @Select("select e.*,d.name deptName from emp e left join  dept  d on e.dept_id=d.id  order by e.update_time desc ")

    List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id")
        //获取到生成的主键
    void insert(Emp emp);

    void deleteByIds(List<Integer> Ids);

    Emp getInfo(Integer id);

    void updateById(Emp emp);

    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    @MapKey("name")
    List<Map<String, Object>> countEmpGender();

    List<Emp> listAll();

    Emp searchBasedOnUsernameAndPassword(Emp emp);
}
