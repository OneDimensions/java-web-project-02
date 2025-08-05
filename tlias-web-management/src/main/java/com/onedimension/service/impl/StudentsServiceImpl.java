package com.onedimension.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onedimension.mapper.StudentsMapper;
import com.onedimension.pojo.PageResult;
import com.onedimension.pojo.Student;
import com.onedimension.pojo.StudentsQueryParams;
import com.onedimension.service.StudentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class StudentsServiceImpl implements StudentsService {
    private final StudentsMapper studentsMapper;

    public StudentsServiceImpl(StudentsMapper studentsMapper) {
        this.studentsMapper = studentsMapper;
    }

    @Override
    public PageResult<Student> page(StudentsQueryParams studentsQueryParams) {
        PageHelper.startPage(studentsQueryParams.getPage(), studentsQueryParams.getPageSize());
        Page<Student> page = (Page<Student>) studentsMapper.page(studentsQueryParams);
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    @Override
    public void save(Student student) {
        LocalDateTime now = LocalDateTime.now();
        student.setCreateTime(now);
        student.setUpdateTime(now);
        studentsMapper.save(student);
    }

    @Override
    public void delete(List<Integer> ids) {
        studentsMapper.deleteById(ids);
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentsMapper.getStudentById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentsMapper.update(student);
    }

    @Override
    public void violation(Integer id, Short score) {
        Student student = studentsMapper.getStudentById(id);
        if (student != null) {
            student.setUpdateTime(LocalDateTime.now());
            student.setViolationScore((short) (student.getViolationScore() + score));
            student.setViolationCount((short) (student.getViolationCount() + 1));
            student.setId(id);
            studentsMapper.update(student);
        }
    }
}
