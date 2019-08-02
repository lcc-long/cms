package com.lcc.cms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 用户角色对象
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_user_role")
public class UserRole {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "u_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "r_id")
    private Role role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
