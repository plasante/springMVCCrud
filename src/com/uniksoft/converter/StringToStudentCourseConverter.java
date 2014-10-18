package com.uniksoft.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import com.uniksoft.form.Course;
import com.uniksoft.form.StudentCourse;
import com.uniksoft.service.EntityServiceImpl;

public class StringToStudentCourseConverter implements Converter<String, StudentCourse>  {

	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityServiceImpl entityService;
	
	public StudentCourse convert(String courseID) {
		int id = -1;
		try {
			id = Integer.parseInt(courseID);
		} catch (NumberFormatException e) {
			throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), 
					TypeDescriptor.valueOf(StudentCourse.class), courseID, null);
		}
		
		@SuppressWarnings("unchecked")
		Course course = (Course) entityService.getEntityById(Course.class, id);
		StudentCourse studentCourse = new StudentCourse();
		studentCourse.setCourse(course);
		return studentCourse;
	}
}
