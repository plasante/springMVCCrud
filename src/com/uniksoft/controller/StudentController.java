package com.uniksoft.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniksoft.form.Course;
import com.uniksoft.form.Student;
import com.uniksoft.form.StudentCourse;
import com.uniksoft.service.EntityServiceImpl;

@Controller
public class StudentController {

	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityServiceImpl entityService;
	
	@Autowired
	private ConversionService conversionService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public String listEntities(Map<String, Object> map) {
		map.put("student", new Student());
		map.put("studentList", entityService.listEntities(Student.class));
		map.put("courseMap", getCoursesMap());
		return "student";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/students/edit/{studentID}", method = RequestMethod.GET)
	public String editEntity(@PathVariable Integer studentID, Map<String, Object> map) {
		map.put("student", entityService.getEntityById(Student.class, studentID));
		map.put("studentList", entityService.listEntities(Student.class));
		map.put("courseMap", getCoursesMap());
		return "student";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/students" , method = RequestMethod.POST)
	public String create(@ModelAttribute("student") @Valid Student student,
			BindingResult result, Map<String, Object> map) {
		if (result.hasErrors()) {
			return "student";
		} else {
			List<StudentCourse> studentCourses = new ArrayList<StudentCourse>();
			for (StudentCourse sc : student.getStudentCourses()) {
				sc.setStudent(student);
				studentCourses.add(sc);
			}
			student.setStudentCourses(studentCourses);
			if (student.getId() == null) {
				entityService.addEntity(student);
			} else 
				entityService.updateEntity(student);
			return "redirect:/students";
		}
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, String> getCoursesMap() {
		Map<String, String> courseMap = new HashMap<String, String>();
		List<Course> courses = entityService.listEntities(Course.class);
		for (Course course : courses) {
			courseMap.put(Integer.toString(course.getId()), course.getCourseName());
		}
		return courseMap;
	}
}
