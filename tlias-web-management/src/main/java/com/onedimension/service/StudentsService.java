package com.onedimension.service;

import com.onedimension.pojo.PageResult;
import com.onedimension.pojo.Student;
import com.onedimension.pojo.StudentsQueryParams;

import java.util.List;

public interface StudentsService {
    PageResult<Student> page(StudentsQueryParams studentsQueryParams);

    void save(Student student);

    void delete(List<Integer> id);

    Student getStudentById(Integer id);

    void update(Student student);

    void violation(Integer id, Short score);
}
