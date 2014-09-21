package com.uniksoft.form;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "ROLES")
public class Role {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@NotEmpty(message = "Please enter a role name.")
	@Size(min = 1, max = 50, message = "Your role name must between 1 and 50 characters")
	@Column(name="ROLE_NAME", unique = true, nullable = false)
	private String roleName;
	
	public Role() {}
	
	public Role(String roleName) {
		this.roleName = roleName;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="ROLES_PRIVILEGES", 
	   joinColumns={@JoinColumn(name="ROLE_ID")}, 
       inverseJoinColumns={@JoinColumn(name="PRIVILEGE_ID")})
	private Set<Privilege> privileges = new HashSet<Privilege>(0);
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<Privilege> getPrivileges() {
		return this.privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
	
}
