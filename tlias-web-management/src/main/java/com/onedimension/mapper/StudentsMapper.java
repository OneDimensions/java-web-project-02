package com.onedimension.mapper;

import com.onedimension.pojo.PageResult;
import com.onedimension.pojo.Student;
import com.onedimension.pojo.StudentsQueryParams;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentsMapper {
    List<Student> page(StudentsQueryParams studentsQueryParams);

    void save(Student student);

    void deleteById(List<Integer> ids);

    Student getStudentById(Integer id);

    void update(Student student);
}
