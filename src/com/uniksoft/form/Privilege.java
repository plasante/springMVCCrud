package com.uniksoft.form;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PRIVILEGES")
public class Privilege {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name="PRIVILEGE_NAME", unique = true, nullable = false)
	private String privilegeName;
	
	public Privilege() {}
	
	public Privilege(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="ROLES_PRIVILEGES", 
    		   joinColumns={@JoinColumn(name="PRIVILEGE_ID")}, 
               inverseJoinColumns={@JoinColumn(name="ROLE_ID")})
	List<Role> roles;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
