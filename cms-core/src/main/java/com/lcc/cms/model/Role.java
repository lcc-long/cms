package com.lcc.cms.model;

import javax.persistence.*;

@Entity
@Table(name = "t_role")
public class Role {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@Enumerated(EnumType.STRING)
	@Column(name = "role_type")
	private RoleType roleType;

	public Role() {
	}

	public Role(int id, String name, RoleType roleType) {
		this.id = id;
		this.id = id;
		this.name = name;
		this.roleType = roleType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
}
