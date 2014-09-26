package com.uniksoft.form;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "COURSES")
public class Course {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;

	@Column(name="COURSE_NAME", unique = true, nullable = false)
	private String courseName;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="course", orphanRemoval=true)
	List<StudentCourse> studentCourse;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<StudentCourse> getStudentCourse() {
		return studentCourse;
	}

	public void setStudentCourse(List<StudentCourse> studentCourse) {
		this.studentCourse = studentCourse;
	}
}
