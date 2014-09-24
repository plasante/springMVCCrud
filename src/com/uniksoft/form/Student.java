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
@Table(name = "STUDENTS")
public class Student {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;

	@Column(name="STUDENT_NAME", unique = true, nullable = false)
	private String studentName;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE , CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "student")
	List<StudentCourse> studentCourse;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public List<StudentCourse> getStudentCourse() {
		return studentCourse;
	}

	public void setStudentCourse(List<StudentCourse> studentCourse) {
		this.studentCourse = studentCourse;
	}
}
