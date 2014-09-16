package com.uniksoft.form;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
	private List<Privilege> privileges;
	
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

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
	
}
