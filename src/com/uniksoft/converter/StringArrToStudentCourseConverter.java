package com.uniksoft.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.uniksoft.form.Course;
import com.uniksoft.form.StudentCourse;
import com.uniksoft.service.EntityServiceImpl;

public class StringArrToStudentCourseConverter implements Converter<String[], List<StudentCourse>>  {

	@Autowired
	private EntityServiceImpl entityService;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StudentCourse> convert(String[] coursesID) {
		List<StudentCourse> studentCourses = new ArrayList<StudentCourse>();
		for (String id : coursesID) {
			Course course = (Course) entityService.getEntityById(Course.class, Integer.parseInt(id));
			StudentCourse studentCourse = new StudentCourse();
			studentCourse.setCourse(course);
			studentCourses.add(studentCourse);
		}
		return studentCourses;
	}
}
